package com.java.springboot.employee_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        description = "EmployeeDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    @Schema(
            description = "Employee First Name"
    )
    @NotEmpty(message = "Employee first name should not be null or empty")
    private String firstName;
    @Schema(
            description = "Employee Last Name"
    )
    @NotEmpty (message = "Employee last name should not be null or empty")
    private String lastName;
    @Schema(
            description = "Employee Email"
    )
    @NotEmpty (message = "Email should not be null or empty")
    @Email(message="Should be in the format abc@gmail.com ")
    private String email;
    private String departmentCode;
}