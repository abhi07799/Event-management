package com.event.exception;

import com.event.dto.ErrorDto;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class CustomGlobalExceptionHandler
{

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAnyException(Exception ex, WebRequest request)
    {
        String path = request.getDescription(false).substring(4);

        ErrorDto errorDto = ErrorDto.builder().timestamp(LocalDateTime.now()).message(ex.getMessage()).error(ex.getLocalizedMessage()).path(path).exceptionStackTrace(ExceptionUtils.getStackTrace(ex)).build();

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex, WebRequest request)
    {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> errorObjects = ex.getBindingResult().getAllErrors();

        for (ObjectError errorObject : errorObjects)
        {
            FieldError fieldError = (FieldError) errorObject;
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        String path = request.getDescription(false).substring(4);

        ErrorDto error = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .error(ex.getLocalizedMessage())
                .message(ex.getMessage())
                .multiErrors(errors)
                .path(path)
                .exceptionStackTrace(ExceptionUtils.getStackTrace(ex))
                .build();

        return new ResponseEntity<ErrorDto>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAuthenticationException(InternalAuthenticationServiceException ex, WebRequest request)
    {
        String path = request.getDescription(false).substring(4);

        ErrorDto error = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .error(ex.getLocalizedMessage())
                .message("Authentication Failed due to an Internal Error Please check Authentication related configurations")
                .path(path)
                .exceptionStackTrace(ExceptionUtils.getStackTrace(ex))
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException ex, WebRequest request)
    {
        String path = request.getDescription(true);

        ErrorDto errorDto = ErrorDto.builder().timestamp(LocalDateTime.now()).message(ex.getMessage()).error(String.valueOf(ex.getCause())).path(path).exceptionStackTrace(ExceptionUtils.getStackTrace(ex)).build();

        return new ResponseEntity<>(errorDto, HttpStatus.FORBIDDEN);
    }
}
