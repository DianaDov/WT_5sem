package com.company.client;
import com.company.client.controller.Command;
import com.company.client.controller.UserScanner;

public class Main {
	
	public static void main(String[] args) {
		UserScanner userScanner = UserScanner.getInstance();
		Command client = new Command();
		String command;
		
		System.out.println(Command.MENU);
		
		do {
			System.out.println("Введите команду");
			command = userScanner.nextCommand().toLowerCase();
			switch (command) {
				case "help":
					System.out.println(Command.MENU);
					break;
				case "signup":
					client.signup();
					break;
				case "signin":
					client.signin();
					break;
				case "getcar":
					client.getcar();
					break;
				case "newcar":
					client.newcar();
					break;
				case "editcar":
					client.editcar();
					break;
				case "exit":
					client.exit();
					continue;
			}
		} while(!command.equals("exit"));
	}
}
