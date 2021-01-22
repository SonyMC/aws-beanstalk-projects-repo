package com.in28minutes.rest.webservices.restfulwebservices.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
	
	// Process flow for worker environmnet : 
	// Note : The 'processTask' caontains logic for the batch service.
	// 1. PUT a message on the Queue 
	// 2. EBS Worker Env will poll on this Queue 
	// 3. When a message is placed on the Queue, it wil send a POST request with the body of teh message to a configurable url
	// 4. Default configurable url her eis '/' i.e. root of te application
	// 5. In short, if we want to trigger a task, put a messag on the queue, which will send a POST reques to teh woker env. using boy of teh message
	// 6. Heere all task detaisl will eb available in message put in the queue, available in the parm '@RequestBody TaskDetails task'.
	//Queue > POST '/' 

	@PostMapping("/")
	public ResponseEntity<Void> processTask(@RequestBody TaskDetails task) {

		if(Math.random() > 0.8) {
			LOGGER.info("Erroring Task {}", task);
			throw new RuntimeException("Error Task");
		}
			
		LOGGER.info("Processing Task {}", task);

		return ResponseEntity.ok().build();//200
	}
}
