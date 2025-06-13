package org.scoula.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration //설정 파일임을 의미
//이 경로의 properties 파일을 읽어 spring 에서 쓸 수 있게 함
@PropertySource({"classpath:/application.properties"})
@MapperScan(basePackages = {"org.scoula.mapper"})
//인터페이스에 해당하는 객체를 자동으로 만들어서 싱글톤을 객체 생성함
//xml 파일이 있는 위치를 스캔해서 찾아라
public class RootConfig {

    //application.properties에서 데이터 베이스 연결 정보 주입
    @Value("${jdbc.driver}") String driver;
    @Value("${jdbc.url}") String url;
    @Value("${jdbc.username}") String username;
    @Value("${jdbc.password}") String password;

    //HikariDBCP 싱글톤 만드는 함수를 하나 정의하자
    @Bean
    public DataSource dataSource() {
        //db연결 설정 --> db연결을 많이 해야함.(DBCP)
        //10개의 연결을 기본을 만든다.! ==> 100, 1000 연결설정수 가능!
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactory.setDataSource(dataSource());

        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());

        return manager;
    }

}