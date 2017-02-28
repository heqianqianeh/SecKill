package dao;

import entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by heqianqian on 2017/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    private int seckillId = 1;

    @Test
    public void findById() throws Exception {
        Seckill seckill = seckillDao.findById(seckillId);
        logger.info(seckill.toString());
    }

    @Test
    public void findByPage() throws Exception {
        List<Seckill> seckillList = seckillDao.findByPage(0,3);
        System.out.println(seckillList);
    }

    @Test
    public void reduceStock() throws Exception {
        int insert = seckillDao.reduceStock(2,new Date());
        System.out.println("insert="+insert);
    }

}