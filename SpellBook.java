import java.util.*;

public class SpellBook {
    // attributes
    private static List<SpellBook> allBooks = new ArrayList<>();
    private static List<SpellBook> availableBooks = new ArrayList<>();

    private String name;
    private String inventor;
    private String type;
    private String serialNumber;

    private boolean rented;
    private Student currentlyRenting; // student currently renting
    private List<Student> rentHistory = new ArrayList<>(); // only add student to list after return
    
    // constructor
    public SpellBook(String serialNumber, String name, String inventor, String type){
        this.serialNumber = serialNumber;
        this.name = name;
        this.inventor = inventor;
        this.type = type;
        // add to all and available books
        allBooks.add(this);
        availableBooks.add(this);
    }
    // methods
    public static List<SpellBook> getAllBooks(){
        return allBooks;
    }

    public static List<SpellBook> getAvailableBooks(){
        return availableBooks;
    }

    public String getName(){
        return name;
    }

    public String getInventor(){
        return inventor;
    }

    public String getType(){
        return type;
    }

    public String getSerialNumber(){
        return serialNumber;
    }

    public List<Student> getRentHistory(){
        return rentHistory;
    }

    public String shortString(){
        return name + " (" + inventor + ")";
    }

    public String longString(){
        return serialNumber + ": " + name + " (" + inventor + ", " + type + ")\n" + getRented();
    }
    public String getRented(){
        if(rented){
            // returns the current student renting the book
            return "Rented by: " + currentlyRenting.getStudentNumber() + ".";
        } else {
            return "Currently available.";
        }
    }
    public void rentToStudent(Student student){
        // remove from available list
        availableBooks.remove(this);
        // change rented status
        this.rented = true;
        // assign currently renting studentnumber
        this.currentlyRenting = student;
    }
    public void relinquishFromStudent(Student student){
        // add back to available list
        availableBooks.add(this);
        // set rented status
        this.rented = false;
        // remove current renter
        this.currentlyRenting = null;
        // add student to history
        rentHistory.add(student);
    }
    public static void clearAllBooks(){
        allBooks.clear();
        availableBooks.clear();
    }

}

