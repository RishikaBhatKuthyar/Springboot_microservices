package com.java.springboot.department_service.controller;

import com.java.springboot.department_service.dto.DepartmentDto;
import com.java.springboot.department_service.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name="CRUD REST APIs for Department Resource",
        description = "CRUD REST APIs- Create Department,Get Department,Update Department,Delete Department"
)
@RestController
@RequestMapping("api/dept")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService deptService;
    @Operation(
            summary = "Create Department REST API",
            description="Create department api is used to save department to the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @PostMapping("create")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto department){
        DepartmentDto savedDepartmentDto= deptService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get department By DepartmentId REST API",
            description="Get department By DepartmentId api is used to get a single department from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )

    @GetMapping("showDept/{department_code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department_code") String departmentCode){
        DepartmentDto department=deptService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(department,HttpStatus.OK);
    }
    @Operation(
            summary = "Get All Departments REST API",
            description = "Get All Departments API is used to fetch all departments from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @GetMapping("showAll")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = deptService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Department By Department Code REST API",
            description = "Update Department API is used to update department information in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @PutMapping("update/{department_code}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("department_code") String departmentCode,
                                                          @RequestBody DepartmentDto department) {
        department.setDepartmentCode(departmentCode);
        DepartmentDto updatedDepartment = deptService.updateDepartment(department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Department By Department Code REST API",
            description = "Delete Department API is used to delete a department from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @DeleteMapping("delete/{departmentCode}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String departmentCode) {
        deptService.deleteDepartment(departmentCode);
        return new ResponseEntity<>("Department Deleted", HttpStatus.OK);
    }
}
