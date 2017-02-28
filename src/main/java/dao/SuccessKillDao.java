package dao;

import entity.SuccessKill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 秒杀成功类的Dao层接口
 * Created by heqianqian on 2017/2/28.
 */
public interface SuccessKillDao {

    /**
     * 插入秒杀成功记录
     * @param seckillId 秒杀商品id
     * @param userPhone 秒杀用户电话号
     * @return 插入记录条数
     */
    int insertSuccessKill(@Param("seckillId") int seckillId,@Param("userPhone") long userPhone);

    /**
     * 查询秒杀成功记录
     * @param seckillId 秒杀记录id
     * @return 秒杀成功记录
     */
    SuccessKill findSuccessKill(@Param("seckillId")int seckillId,@Param("userPhone")long userPhone);
}
