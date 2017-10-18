package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

/**
 * Created by yachao on 17/10/17.
 */
public interface SuccessKilledDao {
    /**
     * Insert purchase details and filter duplicates.
     * @param secKillId
     * @param userPhone
     * @return the rows of insert
     */
    int insertSuccessKilled(long secKillId,long userPhone);

    /**
     * Query SuccessKilled according to ID and carry product object entity of spike.
     * @param secKillId
     * @return
     */
    SuccessKilled queryByIdWithSecKill(long secKillId);
}
