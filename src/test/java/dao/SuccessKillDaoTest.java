package dao;

import entity.SuccessKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by heqianqian on 2017/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKillDaoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SuccessKillDao successKillDao;

    private int seckillId = 1;

    @Test
    public void insertSuccessKill() throws Exception {
        int insert = successKillDao.insertSuccessKill(seckillId,23239);
        System.out.println("insert="+insert);
    }

    @Test
    public void findById() throws Exception {
        SuccessKill successKill = successKillDao.findSuccessKill(seckillId,999L);
        System.out.println(successKill);
        System.out.println(successKill.getSecKill());
    }

}