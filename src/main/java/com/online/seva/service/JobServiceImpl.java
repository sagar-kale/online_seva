package com.online.seva.service;

import com.online.seva.domain.Job;
import com.online.seva.repositories.jpa.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobService")
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean saveJob(Job job) {
        log.info("Job" + job);
        jobRepository.save(job);
        return true;
    }

    @Override
    public List<Job> retrieveAllJobs() {
        return jobRepository.findAll();
    }
}
