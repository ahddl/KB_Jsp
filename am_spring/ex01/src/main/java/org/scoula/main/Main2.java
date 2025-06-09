package org.scoula.main;

import org.scoula.beans.Parrot2;
import org.scoula.beans.Person;
import org.scoula.beans.Person2;
import org.scoula.config.ProjectConfig4;
import org.scoula.config.ProjectConfig5;
import org.scoula.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig5.class);

        Parrot2 parrot = context.getBean(Parrot2.class);
        Person2 person = context.getBean(Person2.class);

        System.out.println(parrot.getName());
        System.out.println(person.getName());
        System.out.println(person.getParrot());
    }
}
