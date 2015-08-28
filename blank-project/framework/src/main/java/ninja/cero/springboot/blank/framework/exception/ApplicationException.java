package ninja.cero.springboot.blank.framework.exception;

import java.text.MessageFormat;

/**
 * アプリケーション例外クラス。
 */
public class ApplicationException extends RuntimeException {
    /** 例外原因 */
    protected Throwable cause;

    /** メッセージ引数 */
    protected Object[] args;

    /** エラー */
    protected HttpErrors error;

    /**
     * コンストラクタ。
     * @param error エラー
     */
    public ApplicationException(HttpErrors error) {
        super();
        this.error = error;
    }

    /**
     * コンストラクタ。
     * @param error エラー
     * @param args  バインド変数
     */
    public ApplicationException(HttpErrors error, String... args) {
        super();
        this.error = error;
        this.args = args;
    }


    /**
     * コンストラクタ。
     * @param error エラー
     * @param cause 元の例外
     */
    public ApplicationException(HttpErrors error, Throwable cause) {
        super(cause);
        this.error = error;
        this.cause = cause;
    }

    /**
     * コンストラクタ。
     * @param error エラー
     * @param cause 元の例外
     * @param args  バインド変数
     */
    public ApplicationException(HttpErrors error, Throwable cause, String... args) {
        super(cause);
        this.error = error;
        this.cause = cause;
        this.args = args;
    }

    /**
     * エラーを返します。
     * @return エラー
     */
    public HttpErrors getError() {
        return this.error;
    }

    /**
     * メッセージ引数一覧を返します。
     * @return メッセージ引数一覧
     */
    public Object[] getArgs() {
        return this.args;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        if (args != null) {
            return "[" + error.name() + "]" + MessageFormat.format(error.getMessage(), args);
        }
        return "[" + error.name() + "]" + error.getMessage();
    }
}
