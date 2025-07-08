package com.bunny.company_service.company;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    List<Company> findAll();

    void createCompany(Company company);

    Company findCompanyById(UUID id);

    Company updateCompanyById(UUID id, Company company);

    boolean deleteCompanyBiId(UUID id);
}
