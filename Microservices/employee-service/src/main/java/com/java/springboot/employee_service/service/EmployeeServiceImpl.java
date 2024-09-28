package com.java.springboot.employee_service.service;

import com.java.springboot.employee_service.dto.ApiResponseDto;
import com.java.springboot.employee_service.dto.DepartmentDto;
import com.java.springboot.employee_service.dto.EmployeeDto;
import com.java.springboot.employee_service.entity.Employee;
import com.java.springboot.employee_service.exception.EmailAlreadyExistsException;
import com.java.springboot.employee_service.exception.ResourceNotFoundException;
import com.java.springboot.employee_service.mapper.AutoEmployeeMapper;
import com.java.springboot.employee_service.mapper.EmployeeMapper;
import com.java.springboot.employee_service.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee=employeeRepo.findByEmail(employeeDto.getEmail());
        if(optionalEmployee.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }
//        Employee employee= AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
//        Employee savedEmployee=employeeRepo.save(employee);
//        EmployeeDto savedEmployeeDto=AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
        Employee employee = EmployeeMapper.MapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepo.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.MapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public ApiResponseDto getEmployeeById(Long employeeId) {
        Employee employee=employeeRepo.findEmployeeById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee","id",employeeId));
        EmployeeDto employeeDto = EmployeeMapper.MapToEmployeeDto(employee);

        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity( "http://localhost:8080/api/dept/showDept/" + employee.getDepartmentCode(), DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employee= employeeRepo.findAll();
        return employee.stream().map((eachemployee)->AutoEmployeeMapper.MAPPER.mapToEmployeeDto(eachemployee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employee) {
        Employee existingEmployee=employeeRepo.findById(employee.getId()).orElseThrow(()-> new ResourceNotFoundException("Employee","id",employee.getId()));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        Employee updatedEmployee=employeeRepo.save(existingEmployee);
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee existingEmployee=employeeRepo.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee","id",employeeId));
        employeeRepo.deleteById(employeeId);

    }
}
