package com.company.model;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"username", "password", "right"})
public class User implements Serializable {
	private static final long serialVersionUID = 4031494800302770260L;
	private String username;
	private String password;
	private UserRight right;
	
	public User() {
		username = "";
		password = "";
		right = UserRight.NONE;
	}
	
	public User(String username, String password, UserRight right) {
		this.username = username;
		this.password = password;
		this.right = right;
	}

	public String getUsername() {
		return username;
	}

	@XmlAttribute
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@XmlAttribute
	public void setPassword(String password) {
		this.password = password;
	}

	public UserRight getRight() {
		return right;
	}

	@XmlAttribute
	public void setRight(UserRight right) {
		this.right = right;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", right=" + right + "]";
	}
}
