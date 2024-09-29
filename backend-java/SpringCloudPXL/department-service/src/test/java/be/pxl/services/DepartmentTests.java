package be.pxl.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.repository.DepartmentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = DepartmentServiceApplication.class)
public class DepartmentTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Container
    private static MySQLContainer sqlContainer = new MySQLContainer("mysql:8.0");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @Test
    public void testCreateDepartment() throws Exception {
        DepartmentRequest departmentRequest = DepartmentRequest.builder()
                .name("Development")
                .organizationId(1L)
                .position("Lead")
                .build();

        String departmentString = objectMapper.writeValueAsString(departmentRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentString))
                .andExpect(status().isCreated());

        assertEquals(3, departmentRepository.findAll().size());
    }

    @Test
    public void testGetAllDepartments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        Department department = Department.builder()
                .name("Marketing")
                .organizationId(1L)
                .position("Manager")
                .build();
        Department savedDepartment = departmentRepository.save(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/" + savedDepartment.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByOrganization() throws Exception {
        Department department = Department.builder()
                .name("Sales")
                .organizationId(1L)
                .position("Sales Representative")
                .build();
        departmentRepository.save(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
