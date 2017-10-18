package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SecKill;

import java.util.Date;
import java.util.List;

/**
 * Created by yachao on 17/10/17.
 */
public interface SecKillDao {
    /**
     * Reduce the number of inventory.
     * @param secKillId
     * @param killTime
     * @return If the number of rows is greater than 1, it's that is updated.
     */
    int reduceNumber(@Param("secKillId") long secKillId, @Param("killTime") Date killTime);

    /**
     * Query SecKill object by id.
     * @param secKillId
     * @return
     */
    SecKill queryById(long secKillId);

    /**
     * Query Seckill product list with offset.
     * @param offset
     * @param limit
     * @return
     */
    List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
