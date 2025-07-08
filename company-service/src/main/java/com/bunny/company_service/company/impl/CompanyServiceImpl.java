package com.bunny.company_service.company.impl;

import com.bunny.company_service.company.Company;
import com.bunny.company_service.company.CompanyRepository;
import com.bunny.company_service.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company findCompanyById(UUID id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company updateCompanyById(UUID id, Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()) {
            Company companyToUpdate = optionalCompany.get();

            if (company.getName() != null) companyToUpdate.setName(company.getName());
            if (company.getDescription() != null) companyToUpdate.setDescription(company.getDescription());
            if (company.getJobs() != null) companyToUpdate.setJobs(company.getJobs());

            companyRepository.save(companyToUpdate);
            return companyToUpdate;
        }
        return null;
    }

    @Override
    public boolean deleteCompanyBiId(UUID id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
