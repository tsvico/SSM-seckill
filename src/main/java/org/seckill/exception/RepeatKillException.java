package org.seckill.exception;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/20 22:00
 *  重复秒杀异常(运行期异常)
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
