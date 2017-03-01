package dto;

/**
 * 暴露秒杀地址类
 * Created by heqianqian on 2017/3/1.
 */
public class Exposer {
    private boolean expose;
    private int seckillId;
    private String md5;
    private long currentTime;
    private long startTime;
    private long endTime;

    public Exposer(boolean expose, int seckillId, String md5) {
        this.expose = expose;
        this.seckillId = seckillId;
        this.md5 = md5;
    }

    public Exposer(boolean expose, int seckillId, long currentTime, long startTime, long endTime) {
        this.expose = expose;
        this.seckillId = seckillId;
        this.currentTime = currentTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isExpose() {
        return expose;
    }

    public void setExpose(boolean expose) {
        this.expose = expose;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "expose=" + expose +
                ", seckillId=" + seckillId +
                ", md5='" + md5 + '\'' +
                ", currentTime=" + currentTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
