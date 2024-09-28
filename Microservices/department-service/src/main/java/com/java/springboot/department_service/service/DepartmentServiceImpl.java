
//Client Input (DepartmentDto) ➡️
//Conversion to Entity (Department) ➡️
//Save to Database (savedDepartment) ➡️
//Conversion to Response DTO (savedDepartmentDto) ➡️
//Response to Client (savedDepartmentDto).

package com.java.springboot.department_service.service;

import com.java.springboot.department_service.dto.DepartmentDto;
import com.java.springboot.department_service.entity.Department;
import com.java.springboot.department_service.exception.DepartmentCodeAlreadyExistsException;
import com.java.springboot.department_service.exception.ResourceNotFoundException;
import com.java.springboot.department_service.mapper.AutoDepartmentMapper;
import com.java.springboot.department_service.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepo.findByDepartmentCode(departmentDto.getDepartmentCode());
        if (optionalDepartment.isPresent()) {
            throw new DepartmentCodeAlreadyExistsException("Department already exists");
        }
        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepo.save(department);
        DepartmentDto savedDepartmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepo.findByDepartmentCode(departmentCode).orElseThrow(()-> new ResourceNotFoundException("Department","departmentCode","code"));
        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> department=departmentRepo.findAll();
        return department.stream().map((eachdepartment)->AutoDepartmentMapper.MAPPER.mapToDepartmentDto(eachdepartment)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto department) {
        Department existingDepartment=departmentRepo.findByDepartmentCode(department.getDepartmentCode()).orElseThrow(()->new ResourceNotFoundException("Department","departmentCode","code"));
        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setDepartmentDescription(department.getDepartmentDescription());
        existingDepartment.setDepartmentCode(department.getDepartmentCode());
        Department updatedDepartment=departmentRepo.save(existingDepartment);
        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(String departmentCode) {
        Department existingDepartment = departmentRepo.findByDepartmentCode(departmentCode).orElseThrow(() -> new ResourceNotFoundException("Department", "departmentCode", "code"));
        departmentRepo.deleteByDepartmentCode(departmentCode);
    }


}