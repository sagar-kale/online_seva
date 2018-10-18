package com.online.seva.service;

import com.online.seva.domain.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    boolean saveJob(Job job);

    List<Job> retrieveAllJobs();

    boolean updateJob(Job job);

    Optional<Job> findByID(String id);

    boolean deleInBatch(List<Job> jobList);
}
