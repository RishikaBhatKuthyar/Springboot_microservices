package com.java.springboot.employee_service.controller;

import com.java.springboot.employee_service.dto.ApiResponseDto;
import com.java.springboot.employee_service.dto.EmployeeDto;
import com.java.springboot.employee_service.entity.Employee;
import com.java.springboot.employee_service.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name="CRUD REST APIs for Employee Resource",
        description = "CRUD REST APIs- Create Employee,Get Employee,Update Employee,Delete Employee"
)
@RestController
@RequestMapping("api/emp")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;
    @Operation(
            summary = "Create Employee REST API",
            description="Create employee api is used to save employee to the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @PostMapping("create")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee=employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get Employee By Id REST API",
            description="Get Employee By Id api is used to get a single employee from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )

    @GetMapping("show/{employeeId}")
    public ResponseEntity<ApiResponseDto> getEmployee(@PathVariable Long employeeId){
        ApiResponseDto apiResponseDto=employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Employees REST API",
            description = "Get All Employees API is used to retrieve all employees from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @GetMapping("/showall")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Employee By Id REST API",
            description = "Update Employee API is used to update an employee's info in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employeeDto) {
        employeeDto.setId(employeeId);
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Employee By Id REST API",
            description = "Delete Employee By Id API is used to delete a single employee from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee Deleted", HttpStatus.OK);
    }

}
