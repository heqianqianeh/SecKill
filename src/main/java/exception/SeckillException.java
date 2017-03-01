package exception;

/**
 * 秒杀异常基类
 * Created by heqianqian on 2017/3/1.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
