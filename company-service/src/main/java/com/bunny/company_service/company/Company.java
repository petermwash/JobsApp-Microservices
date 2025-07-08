package com.bunny.company_service.company;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private List<UUID> jobs;
    private List<UUID> reviews;

    public Company() {
    }

    public Company(UUID id, String name, String description, List<UUID> jobs, List<UUID> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobs = jobs;
        this.reviews = reviews;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UUID> getJobs() {
        return jobs;
    }

    public void setJobs(List<UUID> jobs) {
        this.jobs = jobs;
    }

    public List<UUID> getReviews() {
        return reviews;
    }

    public void setReviews(List<UUID> reviews) {
        this.reviews = reviews;
    }
}
