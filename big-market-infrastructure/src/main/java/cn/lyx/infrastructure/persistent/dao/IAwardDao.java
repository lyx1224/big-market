package cn.lyx.infrastructure.persistent.dao;

import cn.lyx.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lyx
 * @description 奖品表的DAO
 * @since 2024/10/19
 */

@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
