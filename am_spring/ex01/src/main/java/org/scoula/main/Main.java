package org.scoula.main;

import org.scoula.beans.Person;
import org.scoula.config.ProjectConfig4;
import org.scoula.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig4.class);

        Parrot parrot = context.getBean(Parrot.class);
        Person person = context.getBean(Person.class);

        System.out.println(parrot.getName());
        System.out.println(person.getName());
        System.out.println(person.getParrot());
    }
}
