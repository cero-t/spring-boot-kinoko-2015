package ninja.cero.springboot.blank.framework.controller;

import ninja.cero.springboot.blank.framework.exception.ApplicationException;
import ninja.cero.springboot.blank.framework.exception.Errors;
import ninja.cero.springboot.blank.framework.exception.HttpErrors;
import ninja.cero.springboot.blank.framework.exception.RestError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * 例外共通ハンドラ。
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    /** ロガー */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * AppExceptionのハンドリングを行ないます。
     * @param request リクエスト
     * @param ex      AppException
     * @return 例外レスポンス
     */
    @ExceptionHandler(value = ApplicationException.class)
    @ResponseBody
    public ResponseEntity<RestError> handleAppException(HttpServletRequest request, ApplicationException ex) {
        return handleError(request, ex.getError(), ex, ex.getArgs());
    }

    /**
     * RuntimeExceptionのハンドリングを行ないます。
     * @param request リクエスト
     * @param ex      RuntimeException
     * @return 例外レスポンス
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseEntity<RestError> handleException(HttpServletRequest request, RuntimeException ex) {
        return handleError(request, Errors.UNEXPECTED, ex, ex.toString());
    }

    /**
     * 例外をハンドルして例外レスポンスを作成します。
     * @param request リクエスト
     * @param error   エラー種別
     * @param ex      例外
     * @param args    メッセージにバインドするパラメータ
     * @return 例外レスポンス
     */
    protected ResponseEntity<RestError> handleError(HttpServletRequest request, HttpErrors error, Exception ex,
            Object... args) {
        String message = MessageFormat.format(error.getMessage(), args);
        if (error.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
            logger.error(message, ex);
        } else {
            logger.debug(message, ex);
        }

        if (error.getStatus() == HttpStatus.UNAUTHORIZED) {
            return new ResponseEntity<>(error.getStatus());
        }

        RestError restError = new RestError();
        restError.path = request.getRequestURI();
        restError.error = error.name();
        restError.status = error.getStatus()
                .value();
        restError.message = message;
        restError.exception = ex.getClass()
                .getName();

        return new ResponseEntity<>(restError, error.getStatus());
    }

    /**
     * {@inheritDoc}
     * <br>
     * Spring MVCが返す例外をハンドルして例外レスポンスを作成します。
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        RestError restError = new RestError();
        if (request instanceof ServletWebRequest) {
            restError.path = ((ServletWebRequest) request).getRequest()
                    .getRequestURI();
        } else {
            restError.path = request.getContextPath();
        }

        restError.error = status.getReasonPhrase();
        restError.status = status.value();
        restError.message = ex.getMessage();
        restError.exception = ex.getClass()
                .getName();

        return new ResponseEntity<>(restError, status);
    }
}
