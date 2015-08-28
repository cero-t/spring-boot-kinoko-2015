package ninja.cero.springboot.blank.framework.context;

/**
 * 現在の状況を取得するためのコンテキストクラス。
 */
public interface Context {
    /**
     * セッションIDを取得します。
     * @return セッションID
     */
    String getSessionId();
}
