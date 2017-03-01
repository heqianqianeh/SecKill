package exception;

/**
 * 重复秒杀异常
 * Created by heqianqian on 2017/3/1.
 */
public class SeckillRepeatException extends SeckillException {
    public SeckillRepeatException(String message) {
        super(message);
    }

    public SeckillRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
