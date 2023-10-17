package coding.mentor;

import java.util.Scanner;

import coding.mentor.data.Course;
import coding.mentor.data.Mentor;
import coding.mentor.data.User;
import coding.mentor.service.CourseService;
import coding.mentor.service.UserService;

public class Main {
	public static final int REGISTER_COURSE = 1;
	public static final int BACK_TO_COURSE_LIST = 2;
	public static final int LOGIN = 1;
	public static final int REGISTER = 2;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		User loggedInUser = null;
		boolean isLogin = true;
		
		do {
			mainScreen();
			if (mainScreen() != null) {
				isLogin = false;
				loggedInUser = mainScreen();
			}

		} while (isLogin);

		while (!isLogin) {

			CourseService.showAllCourse();
			
			System.out.println("Please input the course id to see detail information: ");
			int courseId = scanner.nextInt();

			if (courseId == 0) {
				UserService.showRegisterCoursesByUser(loggedInUser);
				break;
			}
			Course selectedCourse = courseDetails(courseId - 1);
			registerCourse(selectedCourse, loggedInUser);

			showMentorInCourse(courseId);
		}
	}

	public static User mainScreen() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Coding Mentor!");
		System.out.println("1. Login");
		System.out.println("2. Register");
		int loginOrRegister = scanner.nextInt();

		User loggedInUser = null;

		switch (loginOrRegister) {
		case (LOGIN):
			loggedInUser = login();
			if (loggedInUser == null) {
				return null;
			}
			return loggedInUser;
		case (REGISTER):
			registerUser();
			break;
		default:
			System.out.println("Wrong option! Good bye!");
			break;
		}

		return loggedInUser;
	}

	public static User login() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Input ID: ");
		String id = scanner.next();
		System.out.println("Input password: ");
		String pass = scanner.next();

		User selectedUser = UserService.login(id, pass);

		if (selectedUser == null) {
			System.out.println("Login failed! Good bye!");
			return null;
		}

		System.out.println("Welcome " + selectedUser.getName() + " to Coding Mentor!");
		return selectedUser;
	}

	public static void registerUser() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Input ID: ");
		String id = scanner.next();
		System.out.println("Input password: ");
		String pass = scanner.next();
		System.out.println("Input name: ");
		String name = scanner.next();

		UserService.registerNewUser(id, pass, name);
	}

	public static void registerCourse(Course selectedCourse, User selectedUser) {
		System.out.println("1. Register");
		System.out.println("2. Back to course list");

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please input your choice: ");
		int registerOrBack = scanner.nextInt();

		switch (registerOrBack) {
		case (REGISTER_COURSE):
			UserService.registerNewCourse(selectedCourse, selectedUser);
			courseDetails(selectedCourse.getId());
			break;
		case (BACK_TO_COURSE_LIST):
			CourseService.showAllCourse();
			break;
		default:
			System.out.println("Wrong option! Good bye!");
			break;
		}
	}

	public static Course courseDetails(int courseId) {

		Course course = Course.getCourseFromCourseId(courseId);

		CourseService.showCourseDetails(course);

		return course;
	}

	public static void showMentorInCourse(int courseId) {
		Scanner scanner = new Scanner(System.in);
		Course selectedCourse = courseDetails(courseId - 1);
		System.out.println("Choose 1 to show mentor details");
		int mentorDetails = scanner.nextInt();

		if (mentorDetails == 1) {
			System.out.println("Please input mentor Id: ");
			int selectedMentorId = scanner.nextInt();

			Mentor selectedMentor = Mentor.getMentorFromMentorId(selectedMentorId);

			CourseService.showMentorByCourse(selectedCourse, selectedMentor);
		}
		return;
	}

}