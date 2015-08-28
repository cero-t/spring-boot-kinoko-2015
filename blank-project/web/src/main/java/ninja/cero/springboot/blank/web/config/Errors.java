package ninja.cero.springboot.blank.web.config;

import ninja.cero.springboot.blank.framework.exception.HttpErrors;
import org.springframework.http.HttpStatus;

public enum Errors implements HttpErrors {
    /** PAYMENT_ERROR */
    PAYMENT_ERROR(HttpStatus.PAYMENT_REQUIRED, "ここから先は有料です")

    /** UNEXPECTED */
    ,UNEXPECTED(HttpStatus.INTERNAL_SERVER_ERROR, "想定外のエラーが発生しました。 : {0}");

    /** HTTPステータス */
    protected HttpStatus status;

    /** メッセージ */
    protected String message;

    /**
     * コンストラクタ
     * @param status  ステータス
     * @param message メッセージ
     */
    private Errors(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
