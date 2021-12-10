package com.company.server.controller;
import com.company.model.Car;
import com.company.model.User;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class XmlParser {
	private Storage storage;

	public XmlParser() {
		storage = new Storage();
	}

	public void carToXml(Car car) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Car.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			File file = storage.createFileForCar(car);
			
			if (file != null) {
				marshaller.marshal(car, file);
			}
		} catch (JAXBException e) {
		} catch (IOException e) {
		}
	}

	public Car xmlToCar(int id) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Car.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			File file = storage.getCarFile(id);
			
			Car car;
			if (file != null) {
				car = (Car) unmarshaller.unmarshal(file);
			} else {
				car = new Car();
			}
			
			return car;
		} catch (JAXBException e) {
		} catch (IOException e) {
		}
		
		return null;
	}
	
	public void userToXml(User user) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			File file = storage.createFileForUser(user);
			
			if (file != null) {
				marshaller.marshal(user, file);
			}
		} catch (JAXBException e) {
		} catch (IOException e) {
		}
	}

	public User xmlToUser(String username) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			File file = storage.getUserFile(username);
			
			User user = new User();
			if (file != null) {
				user = (User) unmarshaller.unmarshal(file);
			}

			return user;
		} catch (JAXBException e) {
		} catch (IOException e) {
		}
		
		return null;
	}
}