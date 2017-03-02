package dto;

import entity.SuccessKill;
import eum.SeckillStateEnum;

/**
 * 秒杀执行后返回结果
 * Created by heqianqian on 2017/3/1.
 */
public class Execution {
    private int seckillId;
    private int state;
    private String stateInfo;
    private SuccessKill successKill;

    public Execution(int seckillId, SeckillStateEnum seckillStateEnum, SuccessKill successKill) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.successKill = successKill;
    }

    public Execution(int seckillId,SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKill getSuccessKill() {
        return successKill;
    }

    public void setSuccessKill(SuccessKill successKill) {
        this.successKill = successKill;
    }


    @Override
    public String toString() {
        return "Execution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKill=" + successKill +
                '}';
    }
}
