package com.company.model;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"model", "color", "speed"})
public class Car implements Serializable {
	private static final long serialVersionUID = 1623140296706573943L;
	private int id;
	private String model;
	private String color;
	private int speed;
	
	public Car() {
		id = 0;
		model = "";
		color = "";
		speed = 0;
	}
	
	public Car(int id, String model, String color, int speed) {
		this.id = id;
		this.model = model;
		this.color = color;
		this.speed = speed;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	@XmlElement
	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	@XmlElement
	public void setColor(String color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	@XmlElement
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ","+ "model=" + model + "," + "color=" + color + "," + "speed=" + speed + "]";
	}
	
}
