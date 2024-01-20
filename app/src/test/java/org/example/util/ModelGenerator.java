package org.example.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.example.model.Mobile;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ModelGenerator {
    private Model<Mobile> mobileModel;

    @Autowired
    private Faker faker;

    // инстанс объекта с данными (в MobilControllerTest в комментах написал подробнее про instancio)
    @PostConstruct
    private void init() {
        mobileModel = Instancio.of(Mobile.class)
                .ignore(Select.field(Mobile::getId))
                .supply(Select.field(Mobile::getModel), () -> faker.lorem().word())
                .supply(Select.field(Mobile::getVersion), () -> faker.lorem().word())
                .toModel();
    }
}
