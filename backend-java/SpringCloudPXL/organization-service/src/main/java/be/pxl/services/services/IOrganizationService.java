package be.pxl.services.services;

import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {

    OrganizationResponse findById(Long id);

    OrganizationResponse findByIdWithDepartments(Long id);

    OrganizationResponse findByIdWithDepartmentsAndEmployees(Long id);

    OrganizationResponse findByIdWithEmployees(Long id);
}
