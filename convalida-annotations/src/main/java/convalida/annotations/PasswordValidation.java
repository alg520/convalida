package convalida.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * @author Wellington Costa on 05/06/17.
 */
@Target(FIELD)
@Retention(CLASS)
public @interface PasswordValidation {

    String passwordErrorMessage() default "Password required";

    boolean isConfirmPassword() default false;

    String confirmPasswordErrorMessage() default "Invalid password";

}