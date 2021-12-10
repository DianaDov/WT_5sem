package com.company.client.controller;

import com.company.model.Car;
import com.company.model.User;
import com.company.model.UserRight;

import java.io.IOException;

public class Command {
	public static final String MENU = "Команды:\r\n"
			+ "\t help               - вывод доступных команд,\r\n"
			+ "\t getcar             - получить дело,\r\n"
			+ "\t newcar             - создать дело,\r\n"
			+ "\t editcar            - редактировать дело,\r\n"
			+ "\t signup             - зарегистрироваться,\r\n"
			+ "\t signin             - войти в профиль,\r\n"
	        + "\t exit               - выход";
	
	private Connection connection;
	private Car car;
	private UserScanner userScanner;
	private User user;
	
	public Command() {
		connection = new Connection();
		connection.connect();
		car = new Car();
		userScanner = UserScanner.getInstance();
		user = new User();
	}
	
	public void signup() {
		String username;
		String password;
		UserRight userRole;

		try {
			System.out.println("Введите логин:");
			username = userScanner.nextUsername();
			System.out.println("Введите пароль:");
			password = userScanner.nextPassword();
			System.out.println("1 - админ  2 - пользователь");
			userRole = userScanner.nextBoolean() ? UserRight.ADMIN : UserRight.USER;
			user = new User(username, password, userRole);
			connection.registration(user);
		} catch (IOException e) {
		}
	}
	
	public void signin() {
		String username;
		String password;

		try {
			System.out.println("Введите логин:");
			username = userScanner.nextUsername();
			System.out.println("Введите пароль:");
			password = userScanner.nextPassword();
			user = connection.authorization(username, password);
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}
	
	public void getcar() {
		if (user == null || user.getUsername().equals("")) {
			System.out.println("Необходимо зарегестрироваться или авторизоваться");
			return;
		}
		
		System.out.println("Введите ID дела");
		int id = userScanner.nextInt();
		
		try {
			connection.sendMessage("getcar " + id);
			connection.waitForAnswer();
			car = (Car) connection.receiveFile();
			
			if (car.getId() != 0) {
				System.out.println(car);
			} else {
				System.out.println("Автомобиль не найден");
			}
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}
	
	public void editcar() {
		if (user == null || user.getRight() != UserRight.ADMIN) {
			System.out.println("Необходимы права администратора");
			return;
		}
		
		getcar();
		
		if (car == null || car.getId() == 0) {
			System.out.println("Автомобиль не найден");
			return;
		}
		
		CarBuilder b = new CarBuilder();
		
		car = b.edit(car);
		
		try {
			connection.sendMessage("newcar");
			connection.sendFile(car);
		} catch (IOException e) {
		}
	}
	
	public void newcar() {
		if (user == null || user.getRight() != UserRight.ADMIN) {
			System.out.println("Необходимо  зарегестрироваться или авторизоваться");
			return;
		}
		
		CarBuilder b = new CarBuilder();
		Car car = b.create();
		
		try {
			connection.sendMessage("newcar");
			connection.sendFile(car);
		} catch (IOException e) {
		}
	}
	
	public void exit() {
		try {
			connection.sendMessage("exit");
		} catch (IOException e) {
		}
	}
}
