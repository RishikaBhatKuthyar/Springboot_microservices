package com.java.springboot.department_service.service;

import com.java.springboot.department_service.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment( DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String departmentCode);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto updateDepartment(DepartmentDto department);
    void deleteDepartment(String DepartmentId);

}

