package ninja.cero.springboot.blank.test;

import ninja.cero.springboot.blank.framework.context.Context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestContextConfig {
    @Bean
    @Primary
    public Context context() {
        return new Context() {
            @Override
            public String getSessionId() {
                return "JUNIT";
            }
        };
    }
}
