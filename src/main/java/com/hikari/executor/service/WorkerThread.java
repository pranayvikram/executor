package com.hikari.executor.service;

import com.hikari.executor.configuration.OracleConnectionPool;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
class WorkerThread implements Runnable {

	private Connection connection = null;

	@Override
	public void run() {
		processTask();
	}
	
	private void processTask() {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM SUMMARY WHERE CHID BETWEEN 1001 and 1004";
			connection = OracleConnectionPool.getOracleConnection();
			log.info("Oracle hikari connection acquired.");

			statement = connection.prepareStatement(query);
			statement.setFetchSize(1);
			resultSet = statement.executeQuery();
			StringBuffer data = new StringBuffer();
			while(resultSet.next()) {
				data.append(resultSet.getString("CHID"));
				data.append(resultSet.getString("NAME"));
				data.append(resultSet.getString("ID_KEY"));
			}

			log.info("Data: {}", data.toString());
			log.info("Completed: executor thread name: {}", Thread.currentThread().getName());
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
				log.error("Exception in closing connection for thread: {}", Thread.currentThread().getName());
			}
		}
	}
}