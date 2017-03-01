package service.impl;

import dto.Execution;
import dto.Exposer;
import dto.Result;
import entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.SeckillService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by heqianqian on 2017/3/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getById() throws Exception {
        Seckill seckill = seckillService.getById(1);
        System.out.println(seckill);
    }

    @Test
    public void getAll() throws Exception {
        List<Seckill> seckillList = seckillService.getAll();
        System.out.println(seckillList);
    }

    @Test
    public void exposeUrl() throws Exception {
        Exposer exposer = seckillService.exposeUrl(1);
        System.out.println(exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        String md5 = "5161826e7f2e9b4eaf4675b7916aa51d";
        Result<Execution> executionResult = seckillService.executeSeckill(1,16611,md5);
        System.out.println(executionResult.getData());

    }

}