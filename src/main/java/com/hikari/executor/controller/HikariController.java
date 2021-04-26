package com.hikari.executor.controller;


import com.hikari.executor.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HikariController {

	@Autowired
	private WorkerService workerService;

	public HikariController(WorkerService workerService) {
		this.workerService = workerService;
	}

	@GetMapping(value="messages", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String writeMessage() {
		workerService.threadService();
		return "Completed task successfully!!";
	}
}