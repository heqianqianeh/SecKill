package web;

import dto.Execution;
import dto.Exposer;
import dto.Result;
import entity.Seckill;
import eum.SeckillStateEnum;
import exception.SeckillCloseException;
import exception.SeckillRepeatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.SeckillService;

import java.util.Date;
import java.util.List;

/**
 * 秒杀控制器
 * Created by heqianqian on 2017/3/1.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillControl {

    @Autowired
    private SeckillService seckillService;

    /**
     * 列出秒杀商品清单
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> seckillList = seckillService.getAll();
        model.addAttribute("list",seckillList);
        return "list";
    }

    /**
     * 列出商品详细信息
     */
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") int seckillId, Model model){
        if(seckillId<1){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill==null){
            return "redirect:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    /**
     * 暴露秒杀地址
     */
    @RequestMapping(value = "/{seckillId}/expose",
                method = RequestMethod.GET,
                produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result<Exposer> exposer(@PathVariable("seckillId") int seckillId){
        Result<Exposer> result = null;
        try{
            Exposer exposer = seckillService.exposeUrl(seckillId);
            result = new Result<>(true,exposer);
        }catch(Exception e){
            result = new Result<>(false,e.getMessage());
        }
        return result;
    }

    /**
     * 执行秒杀
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execute",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result<Execution> execute(@PathVariable("seckillId")int seckillId,
                                     @CookieValue(value = "userPhone",required = false) long userPhone,
                                     @PathVariable("md5") String md5){
        if(userPhone==0){
            return new Result<Execution>(false,"电话为空");
        }
        try {
            Execution execution = seckillService.executeSeckill(seckillId,userPhone,md5);
            return new Result<Execution>(true,execution);
        }catch (SeckillRepeatException e1){
            Execution execution = new Execution(seckillId, SeckillStateEnum.REPEATED);
            return new Result<Execution>(true,execution);
        }catch (SeckillCloseException e2){
            Execution execution = new Execution(seckillId, SeckillStateEnum.CLOSED);
            return new Result<Execution>(true,execution);
        }catch (Exception e3){
            Execution execution = new Execution(seckillId, SeckillStateEnum.INNER_ERROR);
            return new Result<Execution>(true,execution);
        }
    }


    /**
     * 访问当前时间
     */
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> getCurTime(){
        return new Result<Long>(true,new Date().getTime());
    }

}
