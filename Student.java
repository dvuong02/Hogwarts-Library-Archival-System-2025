import java.util.*;

public class Student {
    // attributes
    private static List<Student> allStudents = new ArrayList<>();
    private static int currentStudentNumber = 100000;

    private String name;
    private String studentNumber;
    private List<SpellBook> rentedBooks = new ArrayList<>(); // currently rented books
    private List<SpellBook> rentHistory = new ArrayList<>(); // history of rented books

    // constructor
    public Student(String name){
        this.name = name;
        // convert student number to a string
        studentNumber = Integer.toString(currentStudentNumber);
        currentStudentNumber++;

        allStudents.add(this);
    }
    // methods
    public static List<Student> getAllStudents(){
        return allStudents;
    }
    
    public String getName(){
        return name;
    }

    public String getStudentNumber(){
        return studentNumber;
    }

    public String shortString(){
        return studentNumber + ": " + name;
    }

    public List<SpellBook> getRentedBooks(){
        return rentedBooks;
    }

    public List<SpellBook> getRentHistory(){
        return rentHistory;
    }

    public void rentBook(SpellBook book){
        rentedBooks.add(book);
    }

    public void relinquishBook(SpellBook book){
        rentedBooks.remove(book);
        rentHistory.add(book);
    }

    public static void clearAllStudents(){
        allStudents.clear();
        currentStudentNumber = 100000;
    }
}
