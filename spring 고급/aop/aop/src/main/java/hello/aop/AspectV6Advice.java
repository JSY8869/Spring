package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("hello.aop.pointcuts.allOrder() && hello.aop.pointcuts.allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //Before
            log.info("트랜잭션 시작 {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //AfterReturning
            log.info("트랜잭션 커밋 {}", joinPoint.getSignature());
            return result;
        } catch (Throwable e) {
            //AfterThrowing
            log.info("트랜잭션 롤백 {}", joinPoint.getSignature());
            throw e;
        }finally {
            //After
            log.info("리소스 릴리즈 {}", joinPoint.getSignature());
        }
    }

}
