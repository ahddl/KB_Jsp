package org.scoula.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeMapperTest {

    @Test
    void getTime() {
    }


    @Test
    @DisplayName("TimeMapper의 getTime2()")
    public void getTime2() {

        log.info("getTime2");
        log.info(timeMapper.getTime2());

    }
}