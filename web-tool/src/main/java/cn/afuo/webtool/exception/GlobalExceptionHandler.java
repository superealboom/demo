package cn.afuo.webtool.exception;



import cn.afuo.webtool.domain.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常捕获处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebToolException.class)
    public ResponseEntity<Result<?>> handleMyExampleException(WebToolException e) {
        return new ResponseEntity<>(Result.fail().code(5000).message(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
