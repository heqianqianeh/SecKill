package exception;

/**
 * 秒杀关闭异常
 * Created by heqianqian on 2017/3/1.
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
