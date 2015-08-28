package ninja.cero.springboot.blank.framework.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JacksonでJSR-310を利用するために必要なクラス群のビルダ。
 */
public class Jsr310ObjectMapperBuilder {
    /** 日付フォーマット */
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** 日時フォーマット */
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss.SSS");

    /** 日付パースフォーマット */
    protected static final DateTimeFormatter DATE_PARSER = DateTimeFormatter.ofPattern("y[-][/]M[-][/]d");

    /** 日時パースフォーマット */
    protected static final DateTimeFormatter DATE_TIME_PARSER = DateTimeFormatter.ofPattern(
            "y[-][/]M[-][/]d H:m:s.SSS");

    /**
     * ObjectMapperを生成します。
     * @return ObjectMapper
     */
    public static ObjectMapper build() {
        return mapperBuilder().build();
    }

    /**
     * Jackson2ObjectMapperBuilderを生成します。
     * @return Jackson2ObjectMapperBuilder
     */
    public static Jackson2ObjectMapperBuilder mapperBuilder() {
        return Jackson2ObjectMapperBuilder.json()
                .indentOutput(true)
                .serializerByType(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public void serialize(LocalDate value, JsonGenerator jgen,
                            SerializerProvider provider) throws IOException, JsonProcessingException {
                        jgen.writeString(value.format(DATE_FORMATTER));
                    }
                })
                .deserializerByType(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonParser jp,
                            DeserializationContext ctxt) throws IOException, JsonProcessingException {
                        return LocalDate.parse(jp.getValueAsString(), DATE_PARSER);
                    }
                })
                .serializerByType(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public void serialize(LocalDateTime value, JsonGenerator jgen,
                            SerializerProvider provider) throws IOException, JsonProcessingException {
                        jgen.writeString(value.format(DATE_TIME_FORMATTER));
                    }
                })
                .deserializerByType(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonParser jp,
                            DeserializationContext ctxt) throws IOException, JsonProcessingException {
                        return LocalDateTime.parse(jp.getValueAsString(), DATE_TIME_PARSER);
                    }
                })
                .modules(new JSR310Module());
    }

    /**
     * HTTP用のMappingJackson2HttpMessageConverterを生成します。
     * @return MappingJackson2HttpMessageConverter
     */
    public static MappingJackson2HttpMessageConverter httpConverter() {
        return new MappingJackson2HttpMessageConverter(build());
    }

    /**
     * デフォルトコンストラクタ。利用禁止。
     */
    protected Jsr310ObjectMapperBuilder() {
        // Do nothing.
    }
}
