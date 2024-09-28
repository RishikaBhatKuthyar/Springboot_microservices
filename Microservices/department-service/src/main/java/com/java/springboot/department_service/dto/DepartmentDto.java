package com.java.springboot.department_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        description = "DepartmentDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    @Schema(
            description = "Department Name"
    )
    @NotEmpty(message = "Department name should not be null or empty")
    private String departmentName;

    @Schema(
            description = "Department Description"
    )
    private String departmentDescription;

    @Schema(
            description = "Each department has a unique code"
    )
    private String departmentCode;
}

