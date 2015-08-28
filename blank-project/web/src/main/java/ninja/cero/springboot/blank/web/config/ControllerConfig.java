package ninja.cero.springboot.blank.web.config;

import ninja.cero.springboot.blank.framework.controller.ControllerExceptionHandler;
import ninja.cero.springboot.blank.framework.controller.LocalDateEditor;
import ninja.cero.springboot.blank.framework.json.Jsr310ObjectMapperBuilder;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.time.LocalDate;

@Configuration
public class ControllerConfig extends ControllerExceptionHandler {
    /**
     * クエリ文字列や、FormからPOSTした値の変換（セッション中には説明せず）。<br>
     * 空文字はnullに変換することで、たとえば「入力した場合に限り最低8文字のバリデーションを行う」ことができる。<br>
     * LocalDateクラスを
     * @param binder WebDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
    }

    /**
     * Controllerの引数、戻り値に利用するLocalDateのフォーマットを変更する。
     * @return Jackson2ObjectMapperBuilder
     */
    @Bean
    public Jackson2ObjectMapperBuilder mapperBuilder() {
        return Jsr310ObjectMapperBuilder.mapperBuilder();
    }
}
