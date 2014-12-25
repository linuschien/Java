package linuschien.vaadin.application.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "name" })
@AllArgsConstructor
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String email;

	private String phone;

	public User clone() {
		return new User(name, email, phone);
	}

	public void clear() {
		name = "";
		email = "";
		phone = "";
	}

}
