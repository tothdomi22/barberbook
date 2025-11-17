package com.dominik.barberbook.dtos.admin.servicetype;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceTypeDto {
    private Integer id;
    private String type;
    private LocalTime length;
}
