package eum;

/**
 * 秒杀状态枚举类
 * Created by heqianqian on 2017/3/1.
 */
public enum SeckillStateEnum {

    SUCCESS(1,"秒杀成功"),
    REPEATED(-1,"秒杀重复"),
    CLOSED(0,"秒杀关闭"),
    INNER_ERROR(2,"秒杀内部异常");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
}

