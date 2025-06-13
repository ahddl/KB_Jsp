package org.scoula.config;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class RootConfigTest {

    //주입 3가지 --> 필드 위에 주입
    @Autowired
    private DataSource dataSource; // hikariDBCP 들어감

    @Test
    @DisplayName("DataSource 연결이 된다.")
    public void dataSource() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            log.info("dbcp로 부터 연결하나 가지고 왔음");
            log.info(con);
        }

//        Connection con = dataSource.getConnection())
//        log.info("DataSource 준비 완료");
//        log.info(con);
//        con.close(); //-> 안쓴다라고 dbcp객체로 알려줌
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testSqlSessionFactory() {
        try (
                SqlSession session = sqlSessionFactory.openSession();
                Connection con = session.getConnection();
        ) {
            log.info(session);
            log.info(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}