package br.com.zup.orange2.author.validation

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ZipcodeValidator::class])
annotation class Zipcode(val message : String = "Invalid Zipcode.")

@Singleton
class ZipcodeValidator : ConstraintValidator<Zipcode, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Zipcode>,
        context: ConstraintValidatorContext
    ): Boolean {
        //if null, it is not valid
        return value?.matches("[0-9]{5}-[0-9]{3}".toRegex()) ?: false
    }

}
