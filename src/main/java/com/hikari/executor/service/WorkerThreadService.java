package com.hikari.executor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class WorkerThreadService implements WorkerService {

	private int TEST_CYCLE_COUNT = 20;
	private int THREAD_COUNT = 8;

	@Override
	public void threadService() {

		List<WorkerThread> threadList = new ArrayList<>();
		for (int i = 0; i < TEST_CYCLE_COUNT; i++) {
			threadList.add(new WorkerThread());
		}

		ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
		for (WorkerThread workerThread : threadList) {
			executorService.execute(workerThread);
		}

		executorService.shutdown();
		log.info("Executor service shutdown initiated.");

		while (!executorService.isTerminated()) { }
		log.info("Finished all threads!!");
	}
}