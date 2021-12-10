package com.company.client.controller;

import com.company.model.Car;

public class CarBuilder {
	private UserScanner userScanner;
	private Car car;
	
	public CarBuilder() {
		userScanner = UserScanner.getInstance();
		car = new Car();
	}
	
	public CarBuilder(Car car) {
		userScanner = UserScanner.getInstance();
		this.car = car;
	}
	
	public Car create() {
		car = new Car();
		
		inputId();
		inputModel();
		inputColor();
		inputSpeed();
		
		return car;
	}
	
	public Car edit(Car std) {
		this.car = std;
		
		System.out.println("Меню:\r\n"
				+ "\t1 - ID\r\n"
				+ "\t2 - Модель\r\n"
				+ "\t3 - Цвет\r\n"
				+ "\t4 - Скорость\r\n"
				+ "\t5 - Завершить\r\n");
		System.out.println(car);
		
		while(true) {
			System.out.println("Введите номер пункта для редактирования:");
			int number = userScanner.nextInt();
			if (number == 5) {
				break;
			}
			switch (number) {
				case 1:
					inputId();
					break;
				case 2:
					inputModel();
					break;
				case 3:
					inputColor();
					break;
				case 4:
					inputSpeed();
					break;
			}
		}
		
		return car;
	}
	
	private void inputId() {
		System.out.println("Введите ID");
		car.setId(userScanner.nextInt());
	}
	
	private void inputModel() {
		System.out.println("Введите модель");
		car.setModel(userScanner.nextCommand());
	}
	
	private void inputColor() {
		System.out.println("Введите цвет");
		car.setColor(userScanner.nextCommand());
	}
	
	private void inputSpeed() {
		System.out.println("Введите скорость");
		car.setSpeed(userScanner.nextInt());
	}
}
