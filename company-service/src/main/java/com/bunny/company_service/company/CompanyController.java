package com.bunny.company_service.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies")
    public ResponseEntity<String> createCompany(
            @RequestBody Company company
    ) {
        companyService.createCompany(company);

        return new ResponseEntity<>("Company Created!", HttpStatus.CREATED);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> findAllCompany() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> findCompanyById(
            @PathVariable UUID id
    ) {
        Company company = companyService.findCompanyById(id);

        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompanyById(
            @PathVariable UUID id,
            @RequestBody Company company
    ) {
        Company updatedCompany = companyService.updateCompanyById(id, company);

        if (updatedCompany != null) {
            return ResponseEntity.ok(updatedCompany);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompanyById(
            @PathVariable UUID id
    ) {
        boolean companyDeleted = companyService.deleteCompanyBiId(id);

        if (companyDeleted) {
            return ResponseEntity.ok("Company deleted!");
        }

        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
    }

}
