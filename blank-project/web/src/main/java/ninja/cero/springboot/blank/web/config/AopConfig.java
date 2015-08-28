package ninja.cero.springboot.blank.web.config;

import ninja.cero.springboot.blank.framework.aop.DumpLogger;
import ninja.cero.springboot.blank.framework.context.Context;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {
    @Autowired
    protected Context context;

    /**
     * Controller / Service / Daoの呼び出しと戻り時にログ出力を行う。
     * @param joinPoint ProceedingJoinPoint
     * @return 対象処理の戻り値
     * @throws Throwable 対象処理の呼び出し時に発生する例外
     */
    @Around(DumpLogger.TARGET_ALL)
    public Object dump(ProceedingJoinPoint joinPoint) throws Throwable {
        return DumpLogger.dump(joinPoint, context);
    }
}
