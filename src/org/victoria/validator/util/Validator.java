package org.victoria.validator.util;

import org.victoria.validator.model.Person;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Validator {

    ValidatorSupplier on(Person p);

    default Validator thenValidate(Predicate<Person> predicate, String errorMessage) {
        return p -> {
            try {
                on(p).validate();
                if (predicate.test(p)) {
                    return () -> p;
                }
                return () -> {
                    ValidationException validationException = new ValidationException("The object is not valid");
                    validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                    throw validationException;
                };
            } catch (ValidationException validationException) {
                if (predicate.test(p)) {
                    return () -> {
                        throw validationException;
                    };
                }
                validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                return () -> {
                    throw validationException;
                };
            }
        };
    }

    static Validator validate(Predicate<Person> predicate, String errorMessage) {
        return p -> {
            if (predicate.test(p)) {
                return () -> p;
            }
            return () -> {
                ValidationException validationException = new ValidationException("The object is not valid");
                validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                throw validationException;
            };
        };
    }

    interface ValidatorSupplier extends Supplier<Person> {

        default Person validate() {
            return get();
        }
    }

    class ValidationException extends RuntimeException {

        public ValidationException(String errorMessage) {
            super(errorMessage);
        }
    }
}
