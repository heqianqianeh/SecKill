package service.impl;

import dao.SeckillDao;
import dao.SuccessKillDao;
import dto.Execution;
import dto.Exposer;
import dto.Result;
import entity.Seckill;
import entity.SuccessKill;
import eum.SeckillStateEnum;
import exception.SeckillCloseException;
import exception.SeckillException;
import exception.SeckillRepeatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import service.SeckillService;

import java.util.Date;
import java.util.List;

/**
 * 秒杀业务层实现类
 * Created by heqianqian on 2017/2/28.
 */
@Service
public class SeckillServiceImpl implements SeckillService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKillDao successKillDao;

    @Override
    public Seckill getById(int seckillId) {
        return seckillDao.findById(seckillId);
    }

    @Override
    public List<Seckill> getAll() {
        //TODO
        return seckillDao.findByPage(0,3);
    }

    @Override
    public Exposer exposeUrl(int seckillId) {
        Seckill seckill = seckillDao.findById(seckillId);
        long currentTime = new Date().getTime();
        if (seckill.getStartTime().getTime()>currentTime
                || seckill.getEndTime().getTime()<currentTime){//秒杀未开始或者已经结束
            return  new Exposer(false,seckillId,currentTime,seckill.getStartTime().getTime(),seckill.getEndTime().getTime());
        }
        //秒杀开始
        return new Exposer(true,seckillId,getMD5(seckillId));
    }

    private String getMD5(int seckillId){
        String salt = "$^$%^%$&^%%$DFDS";
        String base = seckillId+"/"+salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    @Transactional
    public Execution executeSeckill(int seckillId, long userphone, String md5)
            throws SeckillException, SeckillCloseException, SeckillRepeatException{
        //核对md5
        if(md5==null||!md5.equals(getMD5(seckillId))){
            throw new SeckillException("秒杀异常");
        }
        try{
            //插入秒杀记录
            int insert = successKillDao.insertSuccessKill(seckillId,userphone);
            if(insert<=0){
                throw new SeckillRepeatException("秒杀重复");
            }else{
                //减库存
                int update = seckillDao.reduceStock(seckillId,new Date());
                if(update<=0){
                    throw new SeckillCloseException("秒杀关闭");
                }
                //返回秒杀结果
                SuccessKill successKill = successKillDao.findSuccessKill(seckillId,userphone);
                return new Execution(seckillId, SeckillStateEnum.SUCCESS,successKill);
            }
        }catch (SeckillRepeatException e1){
            throw e1;
        }catch (SeckillCloseException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new SeckillException("秒杀内部异常");
        }
    }
}
