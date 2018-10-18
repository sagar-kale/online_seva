package com.online.seva.service;

import com.online.seva.domain.Job;
import com.online.seva.repositories.jpa.JobRepository;
import com.online.seva.util.FormatDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("jobService")
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private FormatDate formatDate;

    @Override
    public boolean saveJob(Job job) {
        log.info("Job" + job);
        //   job.setStartDate(formatDate.getFormatDate(job.getStartDate()));
        // job.setLastDate(formatDate.getFormatDate(job.getLastDate()));
        jobRepository.save(job);
        return true;
    }

    @Override
    public List<Job> retrieveAllJobs() {
        return jobRepository.findAll().stream().map(job -> {
            job.getJobSubDetails().setDownloadPoster("/jobs/download/poster/" + job.getId());
            return job;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean updateJob(Job job) {
        Optional<Job> byId = jobRepository.findById(job.getId());
        if (!byId.isPresent())
            return false;
        jobRepository.save(job);
        return true;

    }

    @Override
    public Optional<Job> findByID(String id) {
        return jobRepository.findById(id);
    }

    @Override
    public boolean deleInBatch(List<Job> jobList) {
        log.info("deleting following jobs..." + jobList);
        jobRepository.deleteInBatch(jobList);
        return true;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }
}
