package ninja.cero.springboot.blank.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * HTTPサービスの共通エラー。
 */
public interface HttpErrors {
    /**
     * HTTPステータスを取得します。
     * @return HTTPステータス
     */
    HttpStatus getStatus();

    /**
     * メッセージを取得します。
     * @return メッセージ
     */
    String getMessage();

    /**
     * エラー名を取得します。
     * @return エラー名
     */
    String name();
}
