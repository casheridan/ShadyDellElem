import java.lang.reflect.Array;
import java.util.ArrayList;

public class Person {
    String fName;
    String mName;
    char middlel;
    String lName;
    String dob;
    String ssn;
    String phone;
    String address;
    ArrayList<String> allergies;
    public enum Type {
        ADMINISTRATION,
        FACULTY,
        STAFF,
        STUDENT
    }
    Type personType;
    ArrayList<String> courses;

    public Person() {}

    public Person(
            String fName,
            String mName,
            String lName,
            String dob,
            String ssn,
            String phone,
            String address,
            ArrayList<String> allergies,
            String type,
            ArrayList<String> courses
    ) {
        this.fName = fName;
        this.mName = mName;
        this.middlel = mName.charAt(0);
        this.lName = lName;
        this.dob = dob;
        this.ssn = ssn;
        this.phone = phone;
        this.address = address;
        this.allergies = allergies;
        switch (type) {
            case "ADMIN" -> this.personType = Person.Type.ADMINISTRATION;
            case "FACULTY" -> this.personType = Person.Type.FACULTY;
            case "STAFF" -> this.personType = Person.Type.STAFF;
            case "STUDENT" -> this.personType = Person.Type.STUDENT;
            default -> {
                this.personType = Person.Type.STUDENT;
            }
        }
        this.courses = courses;
    }

    public String getType() {
        switch (this.personType) {
            case ADMINISTRATION -> {
                return "Administrator";
            }
            case FACULTY -> {
                return "Faculty";
            }
            case STAFF -> {
                return "Staff";
            }
            case STUDENT -> {
                return "Student";
            }
        }
        return null;
    }

    public String getAllergy(String searchTerm) {
        for (String allergy : allergies) {
            if (allergy.equalsIgnoreCase(searchTerm)) {
                return allergy;
            }
            else {
                return "";
            }
        }
        return "";
    }

    public String getAllergies() {
        return String.join(",", this.allergies);
    }

    public String getCourse(String searchTerm) {
        for (String course : courses) {
            if (course.equalsIgnoreCase(searchTerm)) {
                return course;
            }
            else {
                return "";
            }
        }
        return "";
    }

    public String getCourses() {
        return String.join(",", this.courses);
    }
}
