package com.online.seva.repositories.jpa;

import com.online.seva.domain.Job;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!mongodb"})
public interface JobRepository extends JpaRepository<Job, String> {
}
