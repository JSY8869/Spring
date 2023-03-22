package hello.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class pointcuts {

    @Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
    public void allOrder(){} //pointcut signature

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
