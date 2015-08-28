package ninja.cero.springboot.blank.web.config;

import ninja.cero.springboot.blank.framework.json.Jsr310ObjectMapperBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class HttpClientConfig {
    /**
     * RestTemplateのリクエスト、レスポンスに利用するLocalDateのフォーマットを変更する。
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setMessageConverters(Collections.singletonList(Jsr310ObjectMapperBuilder.httpConverter()));
        return restTemplate;
    }
}
