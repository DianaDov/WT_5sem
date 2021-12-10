package com.company.server.controller;

import com.company.model.Car;
import com.company.model.User;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private static final int PORT = 10000;
	private Connection connection;
	private XmlParser parser;
	
	public Server() {
		parser = new XmlParser();
	}
	
	public void start() {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			connection = new Connection(serverSocket.accept());
			connection.connect();
		} catch (IOException e) {
		} 
	}
	
	public String[] recieveCommand() {
		connection.waitForMessage();
		return connection.recieveMessage().split(" ");
	}
	
	public void registration() {
		User user;
		
		connection.waitForMessage();
		try {
			user = (User) connection.receiveFile();
			parser.userToXml(user);
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public void authorization(String[] message) {
		User user = null;
		
		try {
			if (message.length < 3) {
				connection.sendFile(new User());
				return;
			} 
			
			user = parser.xmlToUser(message[1]);
			
			if (user != null && user.getUsername().equals(message[1]) 
					         && user.getPassword().equals(message[2])) {
				connection.sendFile(user);
			} else {
				connection.sendFile(new User());
			}
		} catch (IOException e) {
		}
	} 

	public void getcar(String[] message) {
		Car file;
		int id;
		
		try {
			
			if (message.length < 2) {
				connection.sendFile(new Car());
				return;
			}
			
			id = Integer.parseInt(message[1]);
			file = parser.xmlToCar(id);
			connection.sendFile(file);
		} catch (IOException e) {
		} catch (NumberFormatException e) {
			try {
				connection.sendFile(new Car());
			} catch (IOException e1) {
			}
		}
	}
	
	public void newcar() {
		try {
			connection.waitForMessage();
			Car car = (Car) connection.receiveFile();

			parser.carToXml(car);
			
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}
	
	public void editcar(String[] message) {
		getcar(message);
		newcar();
	}
	
	public void exit() {
		connection.disconnect();
	}
}
