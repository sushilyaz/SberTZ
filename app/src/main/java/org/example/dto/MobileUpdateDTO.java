package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class MobileUpdateDTO {
    @NotBlank
    private JsonNullable<String> model;

    @NotBlank
    private JsonNullable<String> version;
}
