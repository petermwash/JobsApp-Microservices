package com.bunny.job_service.job.impl;

import com.bunny.job_service.job.Job;
import com.bunny.job_service.job.JobRepository;
import com.bunny.job_service.job.JobService;
import com.bunny.job_service.job.dto.JobWithCompanyDTO;
import com.bunny.job_service.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOList = new ArrayList<>();

        return jobs.stream().map(this::jobToDTOWithCompany).collect(Collectors.toList());
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDTO findJobById(UUID id) {
        Job job = jobRepository.findById(id).orElse(null);

        if (job != null) {

            return jobToDTOWithCompany(job);
        }
        return null;
    }

    @Override
    public boolean deleteJobById(UUID id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Job updateJobById(UUID id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            Job jobToUpdate = jobOptional.get();

            if (job.getTitle() != null) jobToUpdate.setTitle(job.getTitle());
            if (job.getDescription() != null) jobToUpdate.setDescription(job.getDescription());
            if (job.getMinSalary() != null) jobToUpdate.setMinSalary(job.getMinSalary());
            if (job.getMaxSalary() != null) jobToUpdate.setMaxSalary(job.getMaxSalary());
            if (job.getLocation() != null) jobToUpdate.setLocation(job.getLocation());

            jobRepository.save(jobToUpdate);
            return jobToUpdate;
        }
        return null;
    }

    private JobWithCompanyDTO jobToDTOWithCompany(Job job) {
        RestTemplate restTemplate = new RestTemplate();
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);

        Company company = restTemplate.getForObject(
                "http://localhost:8081/companies/" + job.getCompanyId(),
                Company.class
        );
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }
}
