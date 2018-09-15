package model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Human {

	public Admin() {
	}
	private void ableUser(User user) {
	}

	private void disableUser(User user) {
	}

	private void newActivity() {
	}

	private void editActivity(Activity activity, String name) {
	}

	private void deleteActivity(Activity activity) {
	}

}