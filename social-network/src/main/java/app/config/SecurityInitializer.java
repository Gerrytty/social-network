package app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//@Order(2)
//@ComponentScan("app")
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
