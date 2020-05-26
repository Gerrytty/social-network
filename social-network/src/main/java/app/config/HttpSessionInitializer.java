//package app.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration;
//import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
//import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableJdbcHttpSession
//@EnableJpaRepositories
//public class HttpSessionInitializer extends AbstractHttpSessionApplicationInitializer {
//
//    protected HttpSessionInitializer() {
//        super(ApplicationContextConfig.class, PersistenceConfig.class);
//    }
//
//}
//
//
//@Configuration
//class JdbcHttpSessionConfiguration extends SpringHttpSessionConfiguration {
//
//    @Bean
//    public PlatformTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//}