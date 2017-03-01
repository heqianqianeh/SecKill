package service;

import dto.Execution;
import dto.Exposer;
import dto.Result;
import entity.Seckill;

import java.util.List;

/**
 * 秒杀业务层类
 * Created by heqianqian on 2017/2/28.
 */
public interface SeckillService {

    /**
     * 根据id查询秒杀商品类
     * @param seckillId  秒杀商品id
     * @return 秒杀商品类
     */
    Seckill getById(int seckillId);

    /**
     * 查询所有秒杀商品类
     *
     * @return 秒杀商品类集合
     */
    List<Seckill> getAll();

    /**
     * 暴露秒杀地址
     * @param seckillId  秒杀商品id
     */
    Exposer exposeUrl(int seckillId);

    /**
     * 执行秒杀
     * @param seckillId 秒杀商品id
     * @param userphone 用户手机号
     * @param md5 消息加密
     * @return 秒杀结果
     */
    Result<Execution> executeSeckill(int seckillId, long userphone, String md5);
}
