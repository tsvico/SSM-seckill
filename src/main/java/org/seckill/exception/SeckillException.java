package org.seckill.exception;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/21 9:04
 * 功能 秒杀相关业务异常
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
