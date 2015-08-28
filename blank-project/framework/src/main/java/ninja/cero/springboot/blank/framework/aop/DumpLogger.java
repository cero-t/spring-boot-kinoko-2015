package ninja.cero.springboot.blank.framework.aop;

import ninja.cero.springboot.blank.framework.context.Context;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 各レイヤーでロギングを行なうインターセプタ。<br>
 */
public class DumpLogger {
    /** 設定対象 */
    public static final String TARGET_ALL = "execution(* ninja.cero.springboot.blank..*.*(..))"
            + " && (bean(*Controller) || bean(*Logic) || bean(*Dao))";

    /** ロガー */
    protected static final Logger LOGGER = LoggerFactory.getLogger(DumpLogger.class);

    /**
     * 入出力のロギングを行ないます。
     * @param joinPoint 処理対象のメソッド呼び出し
     * @param context   Context
     * @return メソッド呼び出しの実行結果
     * @throws Throwable 処理中に発生した例外
     */
    public static Object dump(ProceedingJoinPoint joinPoint, Context context) throws Throwable {
        if (!LOGGER.isDebugEnabled()) {
            return joinPoint.proceed(joinPoint.getArgs());
        }

        try {
            LOGGER.debug("BEGIN[" + getSessionId(context) + "] - " + toCall(joinPoint));
            LOGGER.trace("with args - " + ToStringBuilder.reflectionToString(joinPoint.getArgs(),
                    ToStringStyle.SHORT_PREFIX_STYLE));
            Object retValue = joinPoint.proceed(joinPoint.getArgs());
            LOGGER.debug("END[" + getSessionId(context) + "] - " + toCall(joinPoint));
            LOGGER.trace(
                    "with return - " + ToStringBuilder.reflectionToString(retValue, ToStringStyle.SHORT_PREFIX_STYLE));
            return retValue;
        } catch (Throwable th) {
            LOGGER.debug("END throw[" + getSessionId(context) + "] - " + toCall(joinPoint));
            LOGGER.debug("Exception: " + th);
            throw th;
        }
    }

    /**
     * セッションIDを取得します。
     * @param context コンテキスト
     * @return セッションID
     */
    protected static String getSessionId(Context context) {
        if (context == null) {
            return "-";
        }
        return context.getSessionId();
    }

    /**
     * メソッド呼び出しをログ用に文字列化します。
     * @param joinPoint メソッド呼び出し
     * @return ログ用の文字列
     */
    private static String toCall(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature()
                .toString();
    }

    /**
     * デフォルトコンストラクタ。利用禁止。
     */
    protected DumpLogger() {
        // Do nothing.
    }
}
