package org.seckill.exception;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/21 9:01
 * 秒杀关闭异常
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
