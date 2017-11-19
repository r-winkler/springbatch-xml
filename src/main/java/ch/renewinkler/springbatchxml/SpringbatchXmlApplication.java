package ch.renewinkler.springbatchxml;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringbatchXmlApplication {

    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        SpringApplication app = new SpringApplication(SpringbatchXmlApplication.class);
        ConfigurableApplicationContext ctx = app.run(args);

        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        Job job = ctx.getBean("xmlImportJob", Job.class);
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        BatchStatus batchStatus = jobExecution.getStatus();

        while (batchStatus.isRunning()) {
            log.info("*********** Still running.... **************");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info(String.format("*********** Exit status: %s", jobExecution.getExitStatus().getExitCode()));
        JobInstance jobInstance = jobExecution.getJobInstance();
        log.info(String.format("********* Name of the job %s", jobInstance.getJobName()));

        log.info(String.format("*********** job instance Id: %d", jobInstance.getId()));

    }

}
