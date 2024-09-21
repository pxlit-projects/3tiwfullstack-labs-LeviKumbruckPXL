package be.pxl.services.controller;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final IEmployeeService employeeService;


    @GetMapping
    public ResponseEntity getAllEmployees() {
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeResponse>> findByDepartment(@PathVariable Long departmentId) {
        return new ResponseEntity<>(employeeService.findByDepartment(departmentId), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<EmployeeResponse>> findByOrganization(@PathVariable Long organizationId) {
        return new ResponseEntity<>(employeeService.findByOrganization(organizationId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }




}