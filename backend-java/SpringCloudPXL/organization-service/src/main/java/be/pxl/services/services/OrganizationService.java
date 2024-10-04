package be.pxl.services.services;

import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {

    private final OrganizationRepository organizationRepository;

    private OrganizationResponse mapToOrganizationResponse(Organization organization) {
        return OrganizationResponse.builder()
                .id(organization.getId())
                .name(organization.getName())
                .address(organization.getAddress())
                .employees(organization.getEmployees())
                .departments(organization.getDepartments())
                .build();
    }

//    @Override
//    public OrganizationResponse findById(Long id) {
//        Optional<Organization> organization = organizationRepository.findById(id);
//        return organization.map(this::mapToOrganizationResponse).orElse(null);
//    }

//    @Override
//    public OrganizationResponse findByIdWithDepartments(Long id) {
//        Optional<Organization> organization = organizationRepository.findByIdWithDepartments(id);
//        return organization.map(this::mapToOrganizationResponse).orElse(null);
//    }
//
//    @Override
//    public OrganizationResponse findByIdWithDepartmentsAndEmployees(Long id) {
//        Optional<Organization> organization = organizationRepository.findByIdWithDepartmentsAndEmployees(id);
//        return organization.map(this::mapToOrganizationResponse).orElse(null);
//    }
//
//    @Override
//    public OrganizationResponse findByIdWithEmployees(Long id) {
//        Optional<Organization> organization = organizationRepository.findByIdWithEmployees(id);
//        return organization.map(this::mapToOrganizationResponse).orElse(null);
//    }
}
