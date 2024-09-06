package com.techverse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
 
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

	 @Bean(name = "taskExecutor")
	    public Executor taskExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(10);
	        executor.setMaxPoolSize(20);
	        executor.setQueueCapacity(100);
	        executor.setThreadNamePrefix("Async-");
	        executor.initialize();
	        return executor;
	    }

    // Optionally override getAsyncUncaughtExceptionHandler to handle async exceptions
}
