package ninja.cero.springboot.blank.framework.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ControllerのリクエストをLocalDateで受けるためのプロパティエディタ。
 */
public class LocalDateEditor extends PropertyEditorSupport {
    /** 日付フォーマット */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String getAsText() {
        LocalDate value = (LocalDate) this.getValue();
        return value.format(FORMATTER);
    }

    @Override
    public void setAsText(String text) {
        if (text == null) {
            setValue((Object) null);
        } else {
            setValue(LocalDate.parse(text, FORMATTER));
        }
    }
}
