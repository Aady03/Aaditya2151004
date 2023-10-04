import java.util.ArrayList;
import java.util.List;

class Student {
    private int studentID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Student(int studentID, String name, String email, String phoneNumber, String address) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}

class Course {
    private int courseID;
    private String courseName;
    private String instructor;
    private int credits;
    private int maxCapacity;
    private List<Student> enrolledStudents = new ArrayList<>();

    public Course(int courseID, String courseName, String instructor, int credits, int maxCapacity) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCredits() {
        return credits;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean isCourseFull() {
        return enrolledStudents.size() >= maxCapacity;
    }

    public void enrollStudent(Student student) {
        if (!isCourseFull()) {
            enrolledStudents.add(student);
        } else {
            System.out.println("Course is already full. Cannot enroll more students.");
        }
    }
}

class Enrollment {
    private int enrollmentID;
    private int studentID;
    private int courseID;
    private String enrollmentDate;

    public Enrollment(int enrollmentID, int studentID, int courseID, String enrollmentDate) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }
}

class CollegeManager {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();
    private int studentCounter = 1;
    private int courseCounter = 1;
    private int enrollmentCounter = 1;

    public void addStudent(String name, String email, String phoneNumber, String address) {
        Student student = new Student(studentCounter++, name, email, phoneNumber, address);
        students.add(student);
    }

    public void addCourse(String courseName, String instructor, int credits, int maxCapacity) {
        Course course = new Course(courseCounter++, courseName, instructor, credits, maxCapacity);
        courses.add(course);
    }

    public void enrollStudentInCourse(int studentID, int courseID, String enrollmentDate) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByID(courseID);

        if (student != null && course != null) {
            if (!course.isCourseFull()) {
                Enrollment enrollment = new Enrollment(enrollmentCounter++, studentID, courseID, enrollmentDate);
                enrollments.add(enrollment);
                course.enrollStudent(student);
                System.out.println("Enrolled student " + student.getName() + " in course " + course.getCourseName());
            } else {
                System.out.println("Course is already full. Cannot enroll more students.");
            }
        } else {
            System.out.println("Student or course not found with the given IDs.");
        }
    }

    public void displayStudentsInCourse(int courseID) {
        Course course = findCourseByID(courseID);

        if (course != null) {
            List<Student> enrolledStudents = course.getEnrolledStudents();
            System.out.println("Students enrolled in course " + course.getCourseName() + ":");
            for (Student student : enrolledStudents) {
                System.out.println("Student ID: " + student.getStudentID() + ", Name: " + student.getName());
            }
        } else {
            System.out.println("Course not found with the given ID.");
        }
    }

    public void displayCoursesForStudent(int studentID) {
        Student student = findStudentByID(studentID);

        if (student != null) {
            System.out.println("Courses enrolled by student " + student.getName() + ":");
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getStudentID() == studentID) {
                    Course course = findCourseByID(enrollment.getCourseID());
                    if (course != null) {
                        System.out.println("Course ID: " + course.getCourseID() + ", Name: " + course.getCourseName());
                    }
                }
            }
        } else {
            System.out.println("Student not found with the given ID.");
        }
    }

    private Student findStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByID(int courseID) {
        for (Course course : courses) {
            if (course.getCourseID() == courseID) {
                return course;
            }
        }
        return null;
    }
}

public class CollegeManagementSystem {
    public static void main(String[] args) {
        CollegeManager collegeManager = new CollegeManager();

        // Adding students to the system
        collegeManager.addStudent("Alice", "alice@example.com", "1234567890", "123 Main St");
        collegeManager.addStudent("Bob", "bob@example.com", "9876543210", "456 Elm St");

        // Adding courses to the system
        collegeManager.addCourse("Math 101", "Dr. Smith", 3, 20);
        collegeManager.addCourse("History 101", "Prof. Johnson", 3, 15);

        // Enrolling students in courses
        collegeManager.enrollStudentInCourse(1, 1, "2023-10-04");
        collegeManager.enrollStudentInCourse(2, 1, "2023-10-04");
        collegeManager.enrollStudentInCourse(1, 2, "2023-10-04");

        // Displaying students in a course
        collegeManager.displayStudentsInCourse(1);

        // Displaying courses for a student
        collegeManager.displayCoursesForStudent(1);
    }
}
