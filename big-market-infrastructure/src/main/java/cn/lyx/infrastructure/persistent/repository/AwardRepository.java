package cn.lyx.infrastructure.persistent.repository;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.lyx.domain.award.model.aggregate.UserAwardRecordAggregate;
import cn.lyx.domain.award.model.entity.TaskEntity;
import cn.lyx.domain.award.model.entity.UserAwardRecordEntity;
import cn.lyx.domain.award.repository.IAwardRepository;
import cn.lyx.infrastructure.event.EventPublisher;
import cn.lyx.infrastructure.persistent.dao.ITaskDao;
import cn.lyx.infrastructure.persistent.dao.IUserAwardRecordDao;
import cn.lyx.infrastructure.persistent.po.Task;
import cn.lyx.infrastructure.persistent.po.UserAwardRecord;
import cn.lyx.types.enums.ResponseCode;
import cn.lyx.types.exception.AppException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.annotation.Resource;

/**
 * @author lyx
 * @description 奖品仓储服务
 * @since 2024/12/4
 */
@Slf4j
@Repository
public class AwardRepository implements IAwardRepository {

    @Resource
    private ITaskDao taskDao;
    @Resource
    private IUserAwardRecordDao userAwardRecordDao;
    @Resource
    private IDBRouterStrategy dbRouter;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private EventPublisher eventPublisher;
    @Override
    public void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate) {
        UserAwardRecordEntity userAwardRecordEntity = userAwardRecordAggregate.getUserAwardRecordEntity();
        TaskEntity taskEntity = userAwardRecordAggregate.getTaskEntity();
        String userId = userAwardRecordEntity.getUserId();
        Long activityId = userAwardRecordEntity.getActivityId();
        Integer awardId = userAwardRecordEntity.getAwardId();

        UserAwardRecord userAwardRecord = new UserAwardRecord();
        userAwardRecord.setUserId(userAwardRecordEntity.getUserId());
        userAwardRecord.setActivityId(userAwardRecordEntity.getActivityId());
        userAwardRecord.setStrategyId(userAwardRecordEntity.getStrategyId());
        userAwardRecord.setOrderId(userAwardRecordEntity.getOrderId());
        userAwardRecord.setAwardId(userAwardRecordEntity.getAwardId());
        userAwardRecord.setAwardTitle(userAwardRecordEntity.getAwardTitle());
        userAwardRecord.setAwardTime(userAwardRecordEntity.getAwardTime());
        userAwardRecord.setAwardState(userAwardRecordEntity.getAwardState().getCode());

        Task task = new Task();
        task.setUserId(taskEntity.getUserId());
        task.setTopic(taskEntity.getTopic());
        task.setMessageId(taskEntity.getMessageId());
        task.setMessage(JSON.toJSONString(taskEntity.getMessage()));
        task.setState(taskEntity.getState().getCode());

        try{
            dbRouter.doRouter(userId);
            transactionTemplate.execute(status -> {
                try {
                    //写入记录
                    userAwardRecordDao.insert(userAwardRecord);
                    //写入任务
                    taskDao.insert(task);
                    return 1;
                }catch (DuplicateKeyException e){
                    status.setRollbackOnly();
                    log.error("写入中奖记录，唯一索引冲突 userId: {} activityId: {} awardId: {}", userId, activityId, awardId, e);
                    throw new AppException(ResponseCode.INDEX_DUP.getCode(),e);
                }
            });
        }finally {
            dbRouter.clear();
        }

        /**
         * 进阶，下列的发送MQ消息可以用线程池进行优化
         */

        try {
            // 发送消息【在事务外执行，如果失败还有任务补偿】
            eventPublisher.publish(task.getTopic(), task.getMessage());
            // 更新数据库记录，task 任务表
            taskDao.updateTaskSendMessageCompleted(task);
        } catch (Exception e) {
            log.error("写入中奖记录，发送MQ消息失败 userId: {} topic: {}", userId, task.getTopic());
            taskDao.updateTaskSendMessageFail(task);
        }

    }
}
