//package app.config;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import javax.servlet.Filter;
//
//@Order(1)
//@ComponentScan("app")
//public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class<?>[] {
//               PersistenceConfig.class, CoreConfig.class, SecurityConfig.class
//        };
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[]{WebMvcConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{
//                new CharacterEncodingFilter("UTF-8", true)
//        };
//    }
//}
