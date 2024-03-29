package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/*
    dto создания новой записи в бд
*/
@Getter
@Setter
public class MobileCreateDTO {
    @NotBlank
    private String model;

    @NotBlank
    private String version;
}
