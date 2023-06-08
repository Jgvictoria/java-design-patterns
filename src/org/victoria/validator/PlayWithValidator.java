package org.victoria.validator;

import org.victoria.validator.model.Person;
import org.victoria.validator.util.Validator;

public class PlayWithValidator {

    public static void main(String[] args) {

        Person sarah = new Person("Sarah", 29);
        Person james = new Person(null, 29);
        Person mary = new Person("Mary", -10);
        Person john = new Person("John", 1_000);
        Person linda = new Person(null, 1_000);

        sarah = Validator.validate(p -> p.getName() != null, "The name should not be null")
                .on(sarah)
                .validate();

        System.out.println("sarah = " + sarah);

        linda = Validator.validate(p -> p.getName() != null, "The name should not be null")
                .thenValidate(p -> p.getAge() > 0, "The age should be greater than zero")
                .thenValidate(p -> p.getAge() < 150, "The age should be lesser than 150")
                .on(linda)
                .validate();

        System.out.println("linda= " + linda);
    }
}
