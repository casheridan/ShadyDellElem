import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class ShadyDell {
    static ArrayList<Person> admins = new ArrayList<>();
    static ArrayList<Person> faculty = new ArrayList<>();
    static ArrayList<Person> staff = new ArrayList<>();
    static ArrayList<Person> students = new ArrayList<>();

    public static void main(String[] args) {
        MenuSelector();
    }

    private static void MenuSelector() {
        System.out.println("What would you like to do?");
        System.out.println("Add Person (add)");
        System.out.println("Search Users (search)");
        System.out.println("Exit Program (exit)");

        Scanner mainIn = new Scanner(System.in);
        String choice = mainIn.nextLine();

        switch (choice) {
            case "add" -> AddPeople();
            case "search" -> SearchUsers();
            case "exit" -> ExitProgram();
            default -> {
                System.out.println("Please choose an option (add, search, or exit)");
                MenuSelector();
            }
        }
    }

    private static void AddPeople() {
        Scanner peopleIn = new Scanner(System.in);
        System.out.print("How many users would you like to create?: ");

        int total = peopleIn.nextInt();
        peopleIn.nextLine();
        int counter = 0;
        while(counter < total) {
            System.out.print("First Name: ");
            String fName = peopleIn.nextLine();
            System.out.print("Middle Name: ");
            String mName = peopleIn.nextLine();
            System.out.print("Last Name: ");
            String lName = peopleIn.nextLine();
            System.out.print("Date of Birth (mm/dd/yyyy): ");
            String dob = peopleIn.nextLine();
            System.out.print("Social Security Number (555-55-5555): ");
            String ssn = peopleIn.nextLine();
            System.out.print("Phone Number (555-555-5555): ");
            String phone = peopleIn.nextLine();
            System.out.print("Address: ");
            String address = peopleIn.nextLine();
            System.out.print("Allergies (seperate with comma): ");
            ArrayList<String> allergies = new ArrayList<>(Arrays.asList(peopleIn.nextLine().split(",")));
            System.out.print("User Type (Admin, Faculty, Staff, Student): ");
            String personType = peopleIn.nextLine().toUpperCase();
            ArrayList<String> courses = new ArrayList<>();
            if (personType.equals("STUDENT") || personType.equals("STAFF")) {
                System.out.print("Courses (seperate with comma): ");
                courses.add(Arrays.toString(peopleIn.nextLine().split(",")));
            }
            else {
                courses.add("N/A");
            }

            Person newPerson = new Person(
                    fName,
                    mName,
                    lName,
                    dob,
                    ssn,
                    phone,
                    address,
                    allergies,
                    personType,
                    courses
            );

            switch (newPerson.personType) {
                case ADMINISTRATION -> admins.add(newPerson);
                case FACULTY -> faculty.add(newPerson);
                case STAFF -> staff.add(newPerson);
                case STUDENT -> students.add(newPerson);
            }

            counter++;
        }

        MenuSelector();
    }

    private static void SearchUsers() {
        Scanner searchIn = new Scanner(System.in);

        System.out.println("Search for User");
        System.out.println("---");
        System.out.println("keywords (first, middle, last, dob, ssn, phone, address, allergy, type, and course");
        System.out.print("Keyword: ");
        String keyword = searchIn.nextLine();
        System.out.print("Search Term: ");
        String searchTerm = searchIn.nextLine();

        ArrayList<Person> searchResults = new ArrayList<>();
        for (ArrayList<Person> list : new ArrayList[]{admins,faculty,staff,students}) {
            for (Person person : list) {
                String valueToCompare = "";
                switch (keyword) {
                    case "first" -> valueToCompare = person.fName;
                    case "middle" -> valueToCompare = person.mName;
                    case "last" -> valueToCompare = person.lName;
                    case "dob" -> valueToCompare = person.dob;
                    case "ssn" -> valueToCompare = person.ssn;
                    case "phone" -> valueToCompare = person.phone;
                    case "address" -> valueToCompare = person.address;
                    case "allergy" -> valueToCompare = person.getAllergy(searchTerm);
                    case "type" -> valueToCompare = person.getType().toLowerCase();
                    case "course" -> valueToCompare = person.getCourse(searchTerm);
                }

                if (valueToCompare.equalsIgnoreCase(searchTerm)) {
                    searchResults.add(person);
                }
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No matching person(s) found...");
            MenuSelector();
        }
        else {
            System.out.println("Search Results: ");
            for (Person person : searchResults) {
                System.out.println(person.getType() + ": " + person.fName + " " + person.middlel + ". " + person.lName + " - " + person.dob + " | " + person.ssn + " | " + person.phone + " | " + person.address + " | " + person.getAllergies() + " | " + person.getCourses());
            }
            MenuSelector();

        }
    }

    private static void ExitProgram() {
        System.exit(0);
    }
}
