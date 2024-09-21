package be.pxl.services.repository;

import be.pxl.services.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByIdWithDepartments(Long id);

    Optional<Organization> findByIdWithDepartmentsAndEmployees(Long id);

    Optional<Organization> findByIdWithEmployees(Long id);
}
