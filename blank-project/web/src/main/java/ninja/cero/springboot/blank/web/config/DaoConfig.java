package ninja.cero.springboot.blank.web.config;

import ninja.cero.sqltemplate.core.SqlTemplate;
import ninja.cero.sqltemplate.core.template.TemplateEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DaoConfig {
    /**
     * Bootiful SQL Templateを利用。FreeMarker形式のテンプレートを利用する。
     * @param jdbcTemplate JdbcTemplate
     * @param namedParameterJdbcTemplate NamedParameterJdbcTemplate
     * @return SqlTemplate
     */
    @Bean
    public SqlTemplate sqlTemplate(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new SqlTemplate(jdbcTemplate, namedParameterJdbcTemplate, TemplateEngine.FREEMARKER);
    }
}
