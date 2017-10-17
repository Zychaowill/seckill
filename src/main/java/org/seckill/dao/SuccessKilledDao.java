package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

/**
 * Created by yachao on 17/10/17.
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复
     * @param secKillId
     * @param userPhone
     * @return插入的行数
     */
    int insertSuccessKilled(long secKillId,long userPhone);

    /**
     *根据ID查询SuccessKilled并携带秒杀产品对象实体
     * @param secKillId
     * @return
     */
    SuccessKilled queryByIdWithSecKill(long secKillId);
}
