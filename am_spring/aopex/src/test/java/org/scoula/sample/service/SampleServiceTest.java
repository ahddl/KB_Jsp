package org.scoula.sample.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)  //JUnit5에서 Spring 테스트 지원
@ContextConfiguration(classes = { RootConfig.class }) //Spring 설정 클래스 지정
@Log4j2
class SampleServiceTest {

    @Autowired
    private SampleService service; //실제로는 Proxy 객체가 주입됨

    @Test
    public void doAdd() throws Exception {
        //정상적인 덧셈 테스트
        log.info(service.doAdd("123", "456"));
    }

    @Test
    public void addError() throws Exception {
        //예외발생 테스트
        log.info(service.doAdd("123", "ABC"));
    }
}