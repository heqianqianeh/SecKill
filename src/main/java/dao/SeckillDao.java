package dao;

import entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 秒杀商品类的Dao层接口
 * Created by heqianqian on 2017/2/28.
 */
public interface SeckillDao {

    /**
     * 根据id查找秒杀商品
     * @param seckillId  秒杀商品id
     * @return 秒杀商品类
     */
    Seckill findById(int seckillId);

    /**
     * 根据页数查找商品
     * @param offset 起始id
     * @param pageSize 每页记录数
     * @return 秒杀商品记录集合
     */
    List<Seckill> findByPage(@Param("offset") int offset,@Param("pageSize") int pageSize);

    /**
     * 减少库存
     * @param seckillId 秒杀商品id
     * @return 更新记录数
     */
    int reduceStock(@Param("seckillId") int seckillId,@Param("killTime") Date killTime);
}
