package cn.lyx.domain.task.service;

import cn.lyx.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author lyx
 * @description 消息任务服务接口
 * @since 2024/12/4
 */
public interface ITaskService {


    /**
     * 查询发送MQ失败和超时1分钟未发送的MQ  【在job中使用】
     *
     * @return 未发送的任务消息列表10条
     */
    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateTaskSendMessageCompleted(String userId, String messageId);

    void updateTaskSendMessageFail(String userId, String messageId);

}
