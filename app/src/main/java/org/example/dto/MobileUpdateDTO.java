package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

// dto обновления
@Getter
@Setter
public class MobileUpdateDTO {
    // обертка JsonNullable используется для частичного обновления
    @NotBlank
    private JsonNullable<String> model;

    @NotBlank
    private JsonNullable<String> version;
}
