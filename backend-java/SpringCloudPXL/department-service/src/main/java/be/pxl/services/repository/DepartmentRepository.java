package be.pxl.services.repository;

import be.pxl.services.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByOrganizationId(Long organizationId);

    @Query("SELECT d FROM Department d JOIN FETCH d.employees WHERE d.organizationId = :organizationId")
    List<Department> findByOrganizationIdWithEmployees(@Param("organizationId") Long organizationId);
}
