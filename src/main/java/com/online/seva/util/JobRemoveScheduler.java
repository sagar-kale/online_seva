package com.online.seva.util;

import com.online.seva.domain.Job;
import com.online.seva.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class JobRemoveScheduler {
    @Autowired
    private JobService jobService;
    @Autowired
    private FormatDate formatDate;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final AtomicBoolean enabled = new AtomicBoolean(false);

    @Scheduled(fixedDelay = 60000)
    //@Scheduled(cron = "0 0 0 * * *", zone = "Indian/Maldives")
    public void executeJob() throws ParseException {
        if (enabled.get()) {
            List<Job> tobeDeleted = new ArrayList<>();
            log.info("executing job.." + new Date().toString());
            List<Job> jobs = jobService.findAll();
            if (jobs != null && !jobs.isEmpty()) {
                log.info("jobs:::" + jobs);
                for (Job job : jobs) {
                    String lastDate = job.getLastDate();
                    log.info("End date of jobs:::" + lastDate);
                    log.info("Start date of jobs:::" + job.getStartDate());

                    Date parsedLastDate = formatDate.getDateFromFormat(job.getLastDate());//dateFormat.parse(dateFormat.format(dateFormat.parse(lastDate)));

                    log.info("parsed after format:::" + dateFormat.format(parsedLastDate));

                    String curr_date_format = dateFormat.format(new Date());
                    Date currentDate = dateFormat.parse(curr_date_format);
                    log.info("current date:::" + dateFormat.format(currentDate));

                    String formattedString = String.format("selected date= %s is before current date=%s", dateFormat.format(parsedLastDate), dateFormat.format(currentDate));
                    if (parsedLastDate.before(currentDate)) {
                        log.info(formattedString);
                        tobeDeleted.add(job);
                    }
                }

            }
            if (null != tobeDeleted && !tobeDeleted.isEmpty()) {
                log.info("deleting jobs::::" + tobeDeleted);
                jobService.deleInBatch(tobeDeleted);
            }
        } else {
            log.info("As of now job is disabled...");
        }
    }

    public boolean toggle() {
        enabled.set(!enabled.get());
        return enabled.get();
    }
}
