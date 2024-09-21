package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .organizationId(departmentRequest.getOrganizationId())
                .name(departmentRequest.getName())
                .position(departmentRequest.getPosition())
                .build();

        departmentRepository.save(department);
    }

    @Override
    public DepartmentResponse findById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(this::mapToDepartmentResponse).orElse(null);
    }

    @Override
    public List<DepartmentResponse> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    @Override
    public List<DepartmentResponse> findByOrganization(Long organizationId) {
        List<Department> departments = departmentRepository.findByOrganizationId(organizationId);
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    @Override
    public List<DepartmentResponse> findByOrganizationWithEmployees(Long organizationId) {
        List<Department> departments = departmentRepository.findByOrganizationIdWithEmployees(organizationId);
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .position(department.getPosition())
                .employees(null) // Replace with actual employee mapping if needed
                .build();
    }
}
