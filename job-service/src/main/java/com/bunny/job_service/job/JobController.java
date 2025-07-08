package com.bunny.job_service.job;

import com.bunny.job_service.job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);

        return new ResponseEntity<>("Job created!", HttpStatus.CREATED);
    }

//    @GetMapping("/jobs")
    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobWithCompanyDTO> findJobById(@PathVariable UUID id) {
        JobWithCompanyDTO job = jobService.findJobById(id);

        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable UUID id) {
        boolean jobDeleted = jobService.deleteJobById(id);

        if (jobDeleted) {
            return new ResponseEntity<>("Job Deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Resource not found!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJobById(
            @PathVariable UUID id,
            @RequestBody Job job
    ) {
        Job updatedJob = jobService.updateJobById(id, job);

        if (updatedJob != null) {
            return new ResponseEntity<>(updatedJob, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
