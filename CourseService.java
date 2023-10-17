package coding.mentor.service;

import coding.mentor.data.Course;
import coding.mentor.data.Mentor;
import coding.mentor.db.Database;

public class CourseService {
	public static void showAllCourse() {
		System.out.println("0. Your course");
		int i = 1;
		for (Course course : Database.COURSES_DB) {
			System.out.println(i + ": ");
			System.out.println("Course ID: " + course.getId());
			System.out.println("Course name: " + course.getName());
			System.out.println("----------");
			i++;
		}
	}

	public static void showCourseDetails(Course selectedCourse) {
		for (Course course : Database.COURSES_DB) {
			if (selectedCourse.getId() == course.getId()) {
				System.out.println("Course ID: " + course.getId());
				System.out.println("Course name: " + course.getName());

				for (int i = 0; i < course.getTeachingMentors().size(); i++) {
					System.out.println("Course mentor: " + course.getTeachingMentors().get(i));
					System.out.println(" -- ");
				}

				System.out.println("Start date: " + course.getBegin());
				System.out.println("End date: " + course.getEnd());
				System.out.println("Fee: $AUD " + course.getFee());
			}
		}
	}

	public static void showMentorByCourse(Course selectedcourse, Mentor selectedMentor) {
		for (Course course : Database.COURSES_DB) {
			if (course.getId() == selectedcourse.getId()) {
				for (int i = 0; i < course.getTeachingMentors().size(); i++) {
					System.out.println("Course mentor: " + course.getTeachingMentors().get(i));
					System.out.println(" -- ");
				}
			}
		}

		for (Mentor mentor : Database.MENTORS_DB) {
			if (selectedMentor.getId() == mentor.getId()) {
				System.out.println("Mentor ID: " + mentor.getId());
				System.out.println("Mentor name: " + mentor.getName());
				System.out.println("Mentor email: " + mentor.getName());
				System.out.println("Mentor phone: " + mentor.getPhone());
			}
		}
	}

}