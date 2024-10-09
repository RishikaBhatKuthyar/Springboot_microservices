package com.java.springboot.employee_service.service;

import com.java.springboot.employee_service.dto.ApiResponseDto;
import com.java.springboot.employee_service.dto.DepartmentDto;
import com.java.springboot.employee_service.dto.EmployeeDto;
import com.java.springboot.employee_service.dto.OrganizationDto;
import com.java.springboot.employee_service.entity.Employee;
import com.java.springboot.employee_service.exception.EmailAlreadyExistsException;
import com.java.springboot.employee_service.exception.ResourceNotFoundException;
import com.java.springboot.employee_service.mapper.AutoEmployeeMapper;
import com.java.springboot.employee_service.mapper.EmployeeMapper;
import com.java.springboot.employee_service.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    // Dependencies
    private EmployeeRepository employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private RestTemplate restTemplate;

    private WebClient webClient;

    private APIClient apiClient;

    // Logger initialization
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    // Save Employee
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        // Check if email already exists
        Optional<Employee> optionalEmployee = employeeRepo.findByEmail(employeeDto.getEmail());
        if (optionalEmployee.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // Map DTO to Entity and save
//        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee employee = EmployeeMapper.MapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepo.save(employee);

        // Convert back to DTO
//        EmployeeDto savedEmployeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.MapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    // Get Employee by ID with Retry functionality
    // @CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDto getEmployeeById(Long employeeId) {
        LOGGER.info("Inside getEmployeeId() Method");

        // Fetch employee from repository
        Employee employee = employeeRepo.findEmployeeById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        EmployeeDto employeeDto = EmployeeMapper.MapToEmployeeDto(employee);

        // Fetch department details using WebClient
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//                "http://localhost:8080/api/dept/showDept/" + employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/dept/showDept/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        OrganizationDto organizationDto=webClient.get()
                .uri("http://localhost:8083/api/org/show/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        // Prepare ApiResponseDto
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    // Fetch all employees
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepo.findAll();
        return employeeList.stream()
                .map(AutoEmployeeMapper.MAPPER::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    // Update Employee
    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        // Find existing employee by ID
        Employee existingEmployee = employeeRepo.findById(employeeDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeDto.getId()));

        // Update employee fields
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());

        // Save updated employee and map back to DTO
        Employee updatedEmployee = employeeRepo.save(existingEmployee);
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(updatedEmployee);
    }

    // Delete Employee by ID
    @Override
    public void deleteEmployee(Long employeeId) {
        // Find existing employee and delete
        Employee existingEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        employeeRepo.deleteById(employeeId);
    }

    // Fallback method for getting default department when the circuit breaker trips or retries fail
    public ApiResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
        LOGGER.info("Inside getDefaultDepartment() Method");

        // Fetch employee and map to DTO
        Employee employee = employeeRepo.findEmployeeById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        EmployeeDto employeeDto = EmployeeMapper.MapToEmployeeDto(employee);

        // Create a default department response
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/dept/showDept/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        // Prepare ApiResponseDto with default department
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
