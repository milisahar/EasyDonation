package com.example.easydonatemaster.configurationfiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@EnableScheduling

public class SchedulerConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
    }

   // @Bean(destroyMethod = "shutdown")
   // public Executor taskScheduler() {
      //  ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
      //  return executor;
   // }
   @Bean(destroyMethod = "shutdown")
   public ThreadPoolTaskScheduler taskScheduler() {
       ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
       scheduler.setPoolSize(10);
       scheduler.setThreadNamePrefix("scheduled-task-");
       scheduler.initialize();
       return scheduler;
   }

}

