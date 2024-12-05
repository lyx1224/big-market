package cn.lyx.domain.task.repository;

import cn.lyx.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author lyx
 * @description 任务服务仓储接口
 * @since 2024/12/4
 */
public interface ITaskRepository {
    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateTaskSendMessageCompleted(String userId, String messageId);

    void updateTaskSendMessageFail(String userId, String messageId);
}
