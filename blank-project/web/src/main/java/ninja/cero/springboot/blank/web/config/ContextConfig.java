package ninja.cero.springboot.blank.web.config;

import ninja.cero.springboot.blank.framework.context.Context;
import ninja.cero.springboot.blank.framework.context.RequestContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class ContextConfig {
    /**
     * HttpServletRequestを利用したContext実装を利用する（AOPでのロギングで利用する）
     * @param request HttpServletRequest
     * @return Context
     */
    @Bean
    public Context context(HttpServletRequest request) {
        return new RequestContext(request);
    }
}
