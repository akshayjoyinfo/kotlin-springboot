package com.wolt.restaurantlisting.common.dto

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter
import java.util.*
import kotlin.collections.ArrayList
import org.springframework.validation.Errors


@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorResponse(
    val status:Int = 0,
    val message: String? = null,
    val stackTrace: String? = null,
    var errors: MutableList<ValidationError>? = null

) {

    @Getter
    @Setter
    @RequiredArgsConstructor
    public class ValidationError(
        val field: String? = null,
        val message: String? = null
    )

    fun addValidationError(field: String?, message: String?) {
        if (Objects.isNull(errors)) {
            errors = ArrayList()
        }
        errors!!.add(ValidationError(field, message))
    }
}