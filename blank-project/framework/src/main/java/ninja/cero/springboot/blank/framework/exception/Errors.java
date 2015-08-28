package ninja.cero.springboot.blank.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * フレームワークのエラー一覧。
 */
public enum Errors implements HttpErrors {
    /** UNEXPECTED */
    UNEXPECTED(HttpStatus.INTERNAL_SERVER_ERROR, "想定外のエラーが発生しました。 : {0}");

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
