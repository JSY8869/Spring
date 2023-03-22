package hello.advanced.trace.strategy.code;

import hello.advanced.trace.strategy.code.template.CallBack;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallBackTest {

    @Test
    void callbackV1() {
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("비즈니스 로직1 실행"));
    }
}
