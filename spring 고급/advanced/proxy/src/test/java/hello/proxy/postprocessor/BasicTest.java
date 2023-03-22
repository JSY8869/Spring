package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);
        A a = applicationContext.getBean("beanA", A.class);
        a.helloA();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(B.class));
    }

    @Slf4j
    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
//        @Bean(name = "beanB")
//        public B b() {
//            return new B();
//        }
    }


    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }
    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }

}
