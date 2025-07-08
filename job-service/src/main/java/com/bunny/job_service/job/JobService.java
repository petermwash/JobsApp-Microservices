package com.bunny.job_service.job;

import com.bunny.job_service.job.dto.JobWithCompanyDTO;

import java.util.List;
import java.util.UUID;

public interface JobService {
    List<JobWithCompanyDTO> findAll();

    void createJob(Job job);

    JobWithCompanyDTO findJobById(UUID id);

    boolean deleteJobById(UUID id);

    Job updateJobById(UUID id, Job job);
}
