package com.wolt.restaurantlisting.common.exceptions

import com.wolt.restaurantlisting.common.dto.ErrorResponse
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    var log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)


    private val printStackTrace = false


    @ExceptionHandler(NoSuchElementFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoSuchElementFoundException(
        itemNotFoundException: NoSuchElementFoundException,
        request: WebRequest
    ): ResponseEntity<Any> {
        log.error("Failed to find the requested element", itemNotFoundException)
        return buildErrorResponse(itemNotFoundException, HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleAllUncaughtException(exception: Exception, request: WebRequest): ResponseEntity<Any> {
        log.error("Unknown error occurred", exception)
        return buildErrorResponse(exception, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request)
    }

    private fun buildErrorResponse(
        exception: Exception,
        httpStatus: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return buildErrorResponse(exception, exception.message, httpStatus, request)
    }

    private fun buildErrorResponse(
        exception: Exception,
        message: String?,
        httpStatus: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(status = httpStatus.value(),
            message= message)

        return ResponseEntity.status(httpStatus).body(errorResponse)
    }

    private fun isTraceOn(request: WebRequest): Boolean {
        val value = request.getParameterValues(TRACE)
        return (Objects.nonNull(value)
                && value!!.size > 0 && value[0].contentEquals("true"))
    }

    override fun handleExceptionInternal(
        ex: java.lang.Exception,
        @Nullable body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return buildErrorResponse(ex, status, request)
    }

    companion object {
        const val TRACE = "trace"

        @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        protected fun handleMethodArgumentNotValid(
            ex: MethodArgumentNotValidException,
            headers: HttpHeaders?,
            status: HttpStatus?,
            request: WebRequest?
        ): ResponseEntity<Any> {
            val errorResponse = ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error. Check 'errors' field for details."
            )
            for (fieldError in ex.bindingResult.fieldErrors) {
                errorResponse.addValidationError(fieldError.field, fieldError.defaultMessage)
            }
            return ResponseEntity.unprocessableEntity().body(errorResponse)
        }
    }
}