package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// dto возврата
@Getter
@Setter
public class MobileDTO {
    private String model;
    private String version;
    private LocalDate createdAt;
}
