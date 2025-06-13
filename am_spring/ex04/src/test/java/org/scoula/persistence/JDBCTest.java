package org.scoula.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.fail;

@Log4j2
public class JDBCTest {

    /*
    여기에 여러 설정 잘 되었는지 함수 만들어서 테스트 여러개 할 예정
    테스트 단위마다 함수 하나 만들어주면 됨
    함수 단위 마다 테스트 해준다고 해서 단위 테스트(Unit test)
    JUnit 라이브러리 사용 예정

    모든 단위 테스트에서 사용핧 드라이버를 설정하는 부분이 필요함

    아래 모든 함수 호출 전에 무조건 한번 이거 실행해줘 라고 할 떄
    @BeforeAll --> 시작할 때 한번 실행함
     */

    @BeforeAll
    public static void setup() {
        //클래스 파일을 연결(예외처리,try-catch)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("JDBC 드라이버 연결이 된다.")
    public void testConnection() {

        String url = "jdbc:mysql://localhost:3306/scoula_db";
        try (Connection con = DriverManager.getConnection(url, "scoula", "1234")) {

            log.info(con);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            fail(e.getMessage());

        }
    }
}