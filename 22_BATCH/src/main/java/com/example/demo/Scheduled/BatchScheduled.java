package com.example.demo.Scheduled;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchScheduled {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job basicJob;

    @Scheduled(initialDelay = 3000,fixedDelay = 10000)
    public void batchScheduled_1() throws Exception {
        log.info("batchScheduled_1 invoke..!");

        JobParameters params = new JobParametersBuilder()
                                .addLong("time",System.currentTimeMillis())
                                .toJobParameters();

        jobLauncher.run(basicJob,params);
    }
}

