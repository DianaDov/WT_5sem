package com.company.server;

import com.company.server.controller.Server;

public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		server.start();

		String[] command = {""};
		
		while (!command[0].equals("exit")) {
			command = server.recieveCommand();

			switch (command[0].toLowerCase()) {
				case "registration":
					server.registration();
					break;
				case "authorization":
					server.authorization(command);
					break;
				case "getcar":
					server.getcar(command);
					break;
				case "newcar":
					server.newcar();
					break;
				case "editcar":
					server.editcar(command);
					break;
				case "exit":
					server.exit();
					continue;
				default:
					break;
			}
		}
		
	}

}
