package com.company.client.controller;

import java.util.Scanner;

public class UserScanner {
	private static UserScanner instance;
	private static Scanner scanner;
	
	private UserScanner() {
		scanner = new Scanner(System.in);
	}
	
	public static UserScanner getInstance() {
		if (instance == null) {
			instance = new UserScanner();
		}
		return instance;
	}
	
	public String nextCommand() {
		System.out.print(">> ");
		return scanner.nextLine();
	}
	
	public String nextUsername() {
		System.out.print(">> ");
		String s = scanner.nextLine();
		return s;
	}
	
	public String nextPassword() {
		System.out.print(">> ");
		String s = scanner.nextLine();
		return s;
	}
	
	public int nextInt() {
		int number = 0;
		System.out.print(">> ");
		while (!scanner.hasNextInt()) {
			scanner.nextLine();
			System.out.print(">> ");
		}
		number = scanner.nextInt();
		scanner.nextLine();
		return number;
	}

	public boolean nextBoolean() {
		int i;
		
		do {
			i = nextInt();
		} while(i < 1 || i > 2);
		
		return i == 1 ? true : false;
	}
}