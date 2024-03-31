package com.shin.common.security.component;

import com.shin.common.core.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

/**
 * @Author shin
 * @Date 2024/3/31 8:55
 */
@RestControllerAdvice
public class GlobalExceptionHandlerResolver {

    /**
     * global exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleGlobalException(Exception exception) {
        return Result.failed(exception.getLocalizedMessage());
    }

    /**
     * validation Exception
     *
     * @return R
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleBodyValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return Result.failed(fieldErrors.get(0).getDefaultMessage());
    }
}
