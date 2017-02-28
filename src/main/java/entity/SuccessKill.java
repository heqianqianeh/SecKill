package entity;

import java.util.Date;

/**
 * 秒杀成功记录类
 * Created by heqianqian on 2017/2/28.
 */
public class SuccessKill {

    private int seckillId;
    private int userPhone;
    private int state;
    private Date createTime;

    //秒杀库存类的引用
    //一对多
    private Seckill secKill;

    public Seckill getSecKill() {
        return secKill;
    }

    public void setSecKill(Seckill secKill) {
        this.secKill = secKill;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", secKill=" + secKill +
                '}';
    }
}
