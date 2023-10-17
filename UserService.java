package coding.mentor.service;

import java.util.ArrayList;

import coding.mentor.data.Course;
import coding.mentor.data.User;
import coding.mentor.db.Database;

public class UserService {
	public static void registerNewUser(String id, String password, String name) {
		for (User user : Database.USERS_DB) {
			if (user.getId().equals(id)) {
				System.out.println("Id has already registered. Try again!");
				return;
			}
		}
		User newUser = new User(id, password, name);
		Database.USERS_DB.add(newUser);
		System.out.println("Register successfully!");
	}

	public static User login(String id, String password) {
		for (User user : Database.USERS_DB) {
			if (user.getId().equals(id)) {
				if (user.getPassword().equals(password)) {
					System.out.println("Login successfully!");
					return user;
				} else {
					int failedCount = user.getFailedCount();
					user.setFailedCount(failedCount + 1);

					if (failedCount >= 3) {
						System.out.println("You have failed to login 3 times. Your account is locked!");
						return null;
					}
				}
			}
		}
		System.out.println("User not found or incorrect password.");
		return null;
	}

	public static void showRegisterCoursesByUser(User selectedUser) {
		for (User user : Database.USERS_DB) {
			if (user.equals(selectedUser)) {
				System.out.println("Your registered course are " + user.getRegisteredCourses());
				return;
			}
		}
		return;
	}

	public static void registerNewCourse(Course selectedCourse, User selectedUser) {
	    for (User user : Database.USERS_DB) {
	        if (user.getId().equals(selectedUser.getId())) {
	            if (user.getRegisteredCourses().contains(selectedCourse)) {
	                System.out.println("Course is already registered! Choose another option!");
	                return;
	            }
	            user.getRegisteredCourses().add(selectedCourse);
	            System.out.println("You have registered the course " + selectedCourse.getName() + " successfully!");
	            return;
	        }
	    }
	}

}