package org.example.spring.qualifier.quickstart.pojo;

import org.example.spring.qualifier.quickstart.pojo.definition.Animal;
import org.example.spring.qualifier.quickstart.pojo.definition.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Song.Z
 */
@Component
public class BusinessPerson implements Person {
    @Autowired
    private Animal dog;

    @Autowired
    private Animal cat;

    @Override
    public void service() {
        this.dog.use();
        this.cat.use();
    }

    @Override
    public void setDog(Animal animal) {
        this.dog = animal;
    }

    @Override
    public void setCat(Animal animal) {
        this.cat = animal;
    }
}
