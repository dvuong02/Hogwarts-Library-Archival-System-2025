import java.util.*;
import java.io.*;

public class Archive {

    // main method
    public static void main(String[] args) {
        // reset all existing students and books from static class lists
        SpellBook.clearAllBooks();
        Student.clearAllStudents();

        // new scanner object to read standard input
        Scanner scan = new Scanner(System.in);
        String input;

        // continuous command prompt loop
        while (true) {
            // header
            System.out.print("user: ");
            // if there are no more commands to be read, break loop
            if (!scan.hasNextLine()) break;
            // scan input and remove extra spaces
            input = scan.nextLine().trim();
            // if this input is the command EXIT, break loop
            if (input.equalsIgnoreCase("EXIT")) break;

            // split input into separate words
            String[] inputs = input.split(" ");
            // case-proof the commands:
            String[] inputsUpper = new String[inputs.length];
            // for each input read, convert to upper case and add to inputsUpper array
            for (int i = 0; i < inputs.length; i++) inputsUpper[i] = inputs[i].toUpperCase();

            // COMMANDS
            if (inputsUpper.length == 1 && inputsUpper[0].equals("COMMANDS")) {
                    System.out.println(
"EXIT ends the archive process\nCOMMANDS outputs this help string\n\nLIST ALL [LONG] outputs either the short or long string for all spellbooks\nLIST AVAILABLE [LONG] outputs either the short or long string for all available spellbooks\nNUMBER COPIES outputs the number of copies of each spellbook\nLIST TYPES outputs the name of every type in the system\nLIST INVENTORS outputs the name of every inventor in the system\n\nTYPE <type> outputs the short string of every spellbook with the specified type\nINVENTOR <inventor> outputs the short string of every spellbook by the specified inventor\n\nSPELLBOOK <serialNumber> [LONG] outputs either the short or long string for the specified spellbook\nSPELLBOOK HISTORY <serialNumber> outputs the rental history of the specified spellbook\n\nSTUDENT <studentNumber> outputs the information of the specified student\nSTUDENT SPELLBOOKS <studentNumber> outputs the spellbooks currently rented by the specified student\nSTUDENT HISTORY <studentNumber> outputs the rental history of the specified student\n\nRENT <studentNumber> <serialNumber> loans out the specified spellbook to the given student\nRELINQUISH <studentNumber> <serialNumber> returns the specified spellbook from the student\nRELINQUISH ALL <studentNumber> returns all spellbooks rented by the specified student\n\nADD STUDENT <name> adds a student to the system\nADD SPELLBOOK <filename> <serialNumber> adds a spellbook to the system\n\nADD COLLECTION <filename> adds a collection of spellbooks to the system\nSAVE COLLECTION <filename> saves the system to a csv file\n\nCOMMON <studentNumber1> <studentNumber2> ... outputs the common spellbooks in students' history\n");
                continue;
            }

            // LIST commands
            if (inputsUpper[0].equals("LIST")) {
                // LIST ALL
                if (inputsUpper.length == 2 && inputsUpper[1].equals("ALL")) {
                    // check if spellbooks exist
                    if (SpellBook.getAllBooks() == null || SpellBook.getAllBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // print out each book in "all books" list
                    for (SpellBook s : SpellBook.getAllBooks()) {
                        System.out.println(s.shortString());
                    }
                    System.out.println();
                    continue;
                }

                // LIST AVAILABLE
                if (inputsUpper.length == 2 && inputsUpper[1].equals("AVAILABLE")) {
                    // check if spellbooks exist
                    if (SpellBook.getAvailableBooks() == null || SpellBook.getAvailableBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // print out each book in "available books" list
                    for (SpellBook s : SpellBook.getAvailableBooks()) {
                        System.out.println(s.shortString());
                    }
                    System.out.println();
                    continue;
                }

                // LIST TYPES
                if (inputsUpper.length == 2 && inputsUpper[1].equals("TYPES")) {
                    // check if spellbooks exist
                    if (SpellBook.getAllBooks() == null || SpellBook.getAllBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // store all types of spellbooks alphabetically in tree set
                    Set<String> types = new TreeSet<>();
                    for (SpellBook s : SpellBook.getAllBooks()) types.add(s.getType());
                    // print each type in the set
                    for (String t : types) System.out.println(t);
                    System.out.println();
                    continue;
                }

                // LIST INVENTORS
                if (inputsUpper.length == 2 && inputsUpper[1].equals("INVENTORS")) {
                    // check if spellbooks exist
                    if (SpellBook.getAllBooks() == null || SpellBook.getAllBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // store all inventors of spellbooks alphabetically in tree set
                    Set<String> inventors = new TreeSet<>();
                    for (SpellBook s : SpellBook.getAllBooks()) inventors.add(s.getInventor());
                    // print each inventor in the set
                    for (String inv : inventors) System.out.println(inv);
                    System.out.println();
                    continue;
                }

                // LIST ALL LONG
                if (inputsUpper.length == 3 && inputsUpper[1].equals("ALL") && inputsUpper[2].equals("LONG")) {
                    // check if spellbooks exist
                    if (SpellBook.getAllBooks() == null || SpellBook.getAllBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // print the long string of each spellbook in "all books" list
                    for (SpellBook s : SpellBook.getAllBooks()) {
                        System.out.println(s.longString()+"\n");
                    }
                    continue;
                } 

                // LIST AVAILABLE LONG
                if (inputsUpper.length == 3 && inputsUpper[1].equals("AVAILABLE") && inputsUpper[2].equals("LONG")) {
                    // check if spellbooks exist
                    if (SpellBook.getAvailableBooks() == null || SpellBook.getAvailableBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // print the long string of each spellbook in "available books" list
                    for (SpellBook s : SpellBook.getAvailableBooks()) {
                        System.out.println(s.longString()+"\n");
                    }
                    continue;
                }
            }

            // NUMBER COPIES
            if (inputsUpper[0].equals("NUMBER") && inputsUpper.length == 2 && inputsUpper[1].equals("COPIES")) {
                // check if spellbooks exist
                if (SpellBook.getAllBooks() == null || SpellBook.getAllBooks().isEmpty()) {
                    System.out.println("No spellbooks in system.\n");
                    continue;
                }
                // add each spellbook short string from "all books" list to treemap key and increment the number of copies in map value
                Map<String, Integer> copies = new TreeMap<>();
                for (SpellBook s : SpellBook.getAllBooks()) {
                    String key = s.shortString();
                    copies.put(key, copies.getOrDefault(key, 0) + 1);
                }
                // print each key and value in alphabetical order
                for (Map.Entry<String, Integer> entry : copies.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println();
                continue;
            }

            // TYPE <type>
            if (inputsUpper.length == 2 && inputsUpper[0].equals("TYPE")) {
                // check if spellbooks exist
                if (SpellBook.getAllBooks().isEmpty()) {
                    System.out.println("No spellbooks in system.\n");
                    continue;
                }
                // initialize string to get type from spellbook matching input in the correct format
                String formattedType = null;
                // determine if a book exists of the input type
                for (SpellBook s : SpellBook.getAllBooks()) {
                    if (s.getType().equalsIgnoreCase(inputsUpper[1])) {
                        formattedType = s.getType();
                        break;
                    }
                }
                // if no matching types are found:
                if (formattedType == null) {
                    System.out.println("No spellbooks with type " + inputs[1] + ".\n");
                    continue;
                }
                // map all spellbooks matching input type alphabetically 
                Map<String, String> results = new TreeMap<>();
                for (SpellBook s : SpellBook.getAllBooks()) {
                    if (s.getType().equals(formattedType)) {
                        results.put(s.getName(), s.getInventor());
                    }
                }
                // print all entries in map
                for (Map.Entry<String, String> entry : results.entrySet()) {
                    System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
                }
                System.out.println();
                continue;
            }

            // INVENTOR <inventor>
            if (inputsUpper.length == 3 && inputsUpper[0].equals("INVENTOR")) {
                // check if spellbooks exist
                if (SpellBook.getAllBooks().isEmpty()) {
                    System.out.println("No spellbooks in system.\n");
                    continue;
                }
                // combine inventor first and last name
                String inventorName = inputs[1]+" "+inputs[2];
                // initialize string to get inventor from spellbook matching input in the correct format
                String formattedInventor = null;
                // determine if a book exists of the input inventor
                for (SpellBook s : SpellBook.getAllBooks()) {
                    if (s.getInventor().equalsIgnoreCase(inventorName)) {
                        formattedInventor = s.getInventor();
                        break;
                    }
                }
                // if no matching types are found:
                if (formattedInventor == null) {
                    System.out.println("No spellbooks by " + inventorName + ".\n");
                    continue;
                }
                // map all spellbooks matching input inventor alphabetically 
                Map<String, String> results = new TreeMap<>();
                for (SpellBook s : SpellBook.getAllBooks()) {
                    if (s.getInventor().equals(formattedInventor)) {
                        results.put(s.getName(), s.getInventor());
                    }
                }
                // print all entries in map
                for (Map.Entry<String, String> entry : results.entrySet()) {
                    System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
                }
                System.out.println();
                continue;
            }

            // SPELLBOOK commands
            if (inputsUpper[0].equals("SPELLBOOK")) {
                // SPELLBOOK <serialNumber>
                if (inputsUpper.length == 2) {
                    // check if spellbooks exist
                    if (SpellBook.getAllBooks() == null || SpellBook.getAllBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // flag for if spellbook is found
                    boolean found = false;
                    // search "all books" list for matching serial number to input
                    for (SpellBook s : SpellBook.getAllBooks()) {
                        if (s.getSerialNumber().equals(inputs[1])) {
                            System.out.println(s.shortString()+"\n");
                            found = true;
                            break;
                        }
                    }
                    // if no matching book:
                    if (!found) {
                        System.out.println("No such spellbook in system.\n");
                    }
                    continue;
                }
                // SPELLBOOK HISTORY <serialNumber>
                if (inputsUpper.length == 3 && inputsUpper[1].equals("HISTORY")) {
                    // flag for if spellbook is found
                    boolean found = false;
                    // search "all books" list for matching book and get its rental history
                    for (SpellBook s : SpellBook.getAllBooks()) {
                        if (s.getSerialNumber().equals(inputs[2])) {
                            found = true;
                            // if no rental history:
                            if (s.getRentHistory().isEmpty()) {
                                System.out.println("No rental history.\n");
                            } else {
                                // print each student number from rental history
                                for (Student st : s.getRentHistory()) System.out.println(st.getStudentNumber());
                                System.out.println();
                            }
                            break;
                        }
                    }
                    // if book not found:
                    if (!found) {
                        System.out.println("No such spellbook in system.\n");
                    }
                    continue;
                }

                // SPELLBOOK <serialNumber> LONG
                if (inputsUpper.length == 3 && inputsUpper[2].equalsIgnoreCase("LONG")) {
                    // check if spellbooks exist
                    if (SpellBook.getAllBooks() == null || SpellBook.getAllBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // flag for if book is found
                    boolean found = false;
                    // search "all books" list for spellbook matching input serial number
                    for (SpellBook s : SpellBook.getAllBooks()) {
                        if (s.getSerialNumber().equals(inputs[1])) {
                            System.out.println(s.longString()+"\n");
                            found = true;
                            break;
                        }
                    }
                    // if spellbook is not found:
                    if (!found) {
                        System.out.println("No such spellbook in system.\n");
                    }
                    continue;
                }
            }

            // STUDENT commands
            if (inputsUpper[0].equals("STUDENT")) {
                // STUDENT <studentNumber>
                if (inputsUpper.length == 2) {
                    // check if students exist
                    if (Student.getAllStudents() == null || Student.getAllStudents().isEmpty()) {
                        System.out.println("No students in system.\n");
                        continue;
                    }
                    // flag for if student is found
                    boolean found = false;
                    // search "all students" list for student matching input
                    for (Student s : Student.getAllStudents()) {
                        if (s.getStudentNumber().equals(inputs[1])) {
                            System.out.println(s.shortString()+"\n");
                            found = true;
                            break;
                        }
                    }
                    // if student is not found:
                    if (!found) {
                        System.out.println("No such student in system.\n");
                    }
                    continue;
                }

                // STUDENT SPELLBOOKS <studentNumber>
                if (inputsUpper.length == 3 && inputsUpper[1].equalsIgnoreCase("SPELLBOOKS")) {
                    // check if students exist
                    if (Student.getAllStudents().isEmpty()) {
                        System.out.println("No students in system.\n");
                        continue;
                    }
                    // flag for if student is found
                    boolean foundstudent = false;
                    // search "all students" list for student matching input and their current rentals
                    for (Student s : Student.getAllStudents()) {
                        if (s.getStudentNumber().equals(inputs[2])) {
                            foundstudent = true;
                            // if no books are being rented:
                            if (s.getRentedBooks().isEmpty()) {
                                System.out.println("Student not currently renting.\n");
                            } else {
                                // print each spellbook in student's rented books
                                for (SpellBook b : s.getRentedBooks()) System.out.println(b.shortString());
                                System.out.println();
                            }
                            break;
                        }
                    }
                    // if student is not found:
                    if (!foundstudent) {
                        System.out.println("No such student in system.\n");
                    }
                    continue;
                }

                // STUDENT HISTORY <studentNumber>
                if (inputsUpper.length == 3 && inputsUpper[1].equalsIgnoreCase("HISTORY")) {
                    // check if students exist
                    if (Student.getAllStudents().isEmpty()) {
                        System.out.println("No students in system.\n");
                        continue;
                    }
                    // flag for if student is found
                    boolean foundstudent = false;
                    // search "all students" list for student matching input
                    for (Student s : Student.getAllStudents()) {
                        if (s.getStudentNumber().equals(inputs[2])) {
                            foundstudent = true;
                            // if no books have previously been rented:
                            if (s.getRentHistory().isEmpty()) {
                                System.out.println("No rental history for student.\n");
                            } else {
                                // print each spellbook in student's rental history
                                for (SpellBook b : s.getRentHistory()) System.out.println(b.shortString());
                                System.out.println();
                            }
                            break;
                        }
                    }
                    // if student is not found:
                    if (!foundstudent) {
                        System.out.println("No such student in system.\n");
                    }
                    continue;
                }
            }

            // RENT <studentNumber> <serialNumber>
            if (inputsUpper[0].equals("RENT") && inputsUpper.length == 3) {
                // check if students exist
                if (Student.getAllStudents().isEmpty()) {
                    System.out.println("No students in system.\n");
                    continue;
                }
                // check if spellbooks exist
                if (SpellBook.getAllBooks().isEmpty()) {
                    System.out.println("No spellbooks in system.\n");
                    continue;
                }
                // flags for if student and book are found, and if book is rented
                boolean foundstudent = false;
                boolean foundbook = false;
                boolean rentedbook = true;
                // student and spellbook variables to link book with student
                Student student = null;
                SpellBook book = null;
                // search "all students" list for student matching input
                for (Student s : Student.getAllStudents()) {
                    if (s.getStudentNumber().equals(inputs[1])) {
                        foundstudent = true;
                        student = s;
                        break;
                    }
                }
                // if student is not found:
                if (!foundstudent) {
                    System.out.println("No such student in system.\n");
                    continue;
                }
                // search "all books" list for spellbook matching input
                for (SpellBook sb : SpellBook.getAllBooks()) {
                    if (sb.getSerialNumber().equals(inputs[2])) {
                        foundbook = true;
                        book = sb;
                        break;
                    }
                }
                // if spellbook is not found:
                if (!foundbook) {
                    System.out.println("No such book in system.\n");
                    continue;
                }
                // search "available books" list for book matching input
                for (SpellBook sba : SpellBook.getAvailableBooks()) {
                    if (sba.getSerialNumber().equals(inputs[2])) {
                        rentedbook = false;
                        sba.rentToStudent(student);
                        student.rentBook(book);
                        System.out.println("Success.\n");
                        break;
                    }
                }
                // if book exists but is not in available books list:
                if (rentedbook) {
                    System.out.println("Spellbook is currently unavailable.\n");
                }
                continue;
            }

            // RELINQUISH commands
            if (inputsUpper[0].equals("RELINQUISH")) {
                if (Student.getAllStudents().isEmpty()) {
                    System.out.println("No students in system.\n");
                    continue;
                }
                // flag for if student is found
                boolean foundstudent = false;
                // student variable to relinquish from
                Student student = null;

                // RELINQUISH ALL <studentNumber>
                if (inputsUpper.length == 3 && inputsUpper[1].equalsIgnoreCase("ALL")) {
                    // search "all students" list for student matching input
                    for (Student s : Student.getAllStudents()) {
                        if (s.getStudentNumber().equals(inputs[2])) {
                            foundstudent = true;
                            student = s;
                            break;
                        }
                    }
                    // if student is not found:
                    if (!foundstudent) {
                        System.out.println("No such student in system.\n");
                        continue;
                    }
                    // if spellbooks do not exist:
                    if (SpellBook.getAllBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // if student has no current book rentals
                    if (student.getRentedBooks().isEmpty()) {
                        System.out.println("Unable to return spellbook.\n");
                        continue;
                    }
                    // create list to store all student book rentals to be returned
                    List<SpellBook> booksToReturn = new ArrayList<>(student.getRentedBooks());
                    for (SpellBook book : booksToReturn) {
                        student.relinquishBook(book);
                        book.relinquishFromStudent(student);
                    }
                    System.out.println("Success.\n");
                    continue;
                }

                // RELINQUISH <studentNumber> <SerialNumber>
                if (inputsUpper.length == 3) {
                    // flag for if book is found
                    boolean foundbook = false;
                    // search "all students" list for student matching input
                    for (Student s : Student.getAllStudents()) {
                        if (s.getStudentNumber().equals(inputs[1])) {
                            foundstudent = true;
                            student = s;
                            break;
                        }
                    }
                    // if student is not found:
                    if (!foundstudent) {
                        System.out.println("No such student in system.\n");
                        continue;
                    }
                    // if spellbooks do not exist:
                    if (SpellBook.getAllBooks().isEmpty()) {
                        System.out.println("No spellbooks in system.\n");
                        continue;
                    }
                    // if student has no current book rentals
                    if (student.getRentedBooks().isEmpty()) {
                        System.out.println("Unable to return spellbook.\n");
                        continue;
                    }
                    // create a new array list to loop through so that the original rentedBooks list can be modified during the loop without issue
                    for (SpellBook book : new ArrayList<>(student.getRentedBooks())) {
                        // if serial number matches input:
                        if (book.getSerialNumber().equals(inputs[2])) {
                            foundbook = true;
                            student.relinquishBook(book);
                            book.relinquishFromStudent(student);
                            System.out.println("Success.\n");
                            break;
                        }
                    }
                    // if book is not found in student's current rentals:
                    if (!foundbook) {
                        System.out.println("Unable to return spellbook.\n");
                    }
                    continue;
                }
            }

            // ADD commands
            if (inputsUpper[0].equals("ADD") && inputsUpper.length >= 2) {
                // ADD SPELLBOOK <filename> <serialNumber>
                if (inputsUpper.length == 4 && inputsUpper[1].equals("SPELLBOOK")) {
                    // attempt to read input file
                    try {
                        File collectionFile = new File(inputs[2]);
                        Scanner fileScan = new Scanner(collectionFile);
                        // skip file header
                        if (fileScan.hasNextLine()) fileScan.nextLine();
                        // flag for if input spellbook is found in file
                        boolean foundInFile = false;
                        boolean exists = false;
                        // scan each spellbook in file and split into serial number, title, inventor and type
                        while (fileScan.hasNextLine()) {
                            String spellBookLine = fileScan.nextLine();
                            String[] bookElements = spellBookLine.trim().split(",");
                            if (bookElements.length != 4) continue;
                            String serialNumber = bookElements[0].trim();
                            String bookName = bookElements[1].trim();
                            String inventor = bookElements[2].trim();
                            String type = bookElements[3].trim();
                            // check if serial number matches input serial number
                            if (!serialNumber.equals(inputs[3])) continue;
                            foundInFile = true;
                            // check if this spellbook already exists in the system
                            for (SpellBook sb : SpellBook.getAllBooks()) {
                                if (sb.getSerialNumber().equals(serialNumber)) {
                                    System.out.println("Spellbook already exists in system.\n");
                                    fileScan.close();
                                    exists = true;
                                    break;
                                }
                            }
                            if(exists) break;
                            // add the spellbook
                            SpellBook newBook = new SpellBook(serialNumber, bookName, inventor, type);
                            System.out.println("Successfully added: " + newBook.shortString() + ".\n");
                            fileScan.close();
                            break;
                        }
                        fileScan.close();
                        // if spellbook is not found in file:
                        if (!foundInFile) {
                            System.out.println("No such spellbook in file.\n");
                        }
                    // if file is not found:
                    } catch (FileNotFoundException e) {
                        System.out.println("No such file.\n");
                    }
                }

                // ADD COLLECTION <filename>
                if (inputsUpper.length == 3 && inputsUpper[1].equals("COLLECTION")) {
                    // attempt to read the input file
                    try {
                        String filename = inputs[2];
                        // add .csv file extension to input if missing
                        if (!filename.endsWith(".csv")) filename += ".csv";
                        File collectionFile = new File(filename);
                        Scanner fileScan = new Scanner(collectionFile);
                        // counter to determine how many books are successfully added from file
                        int booksFound = 0;
                        // skip the file header
                        if (fileScan.hasNextLine()) fileScan.nextLine();
                        // scan each spellbook in the file, splitting into serial number, title, inventor and type
                        while (fileScan.hasNextLine()) {
                            String spellBookLine = fileScan.nextLine();
                            String[] bookElements = spellBookLine.trim().split(",");
                            if (bookElements.length != 4) continue;
                            String serialNumber = bookElements[0].trim();
                            String bookName = bookElements[1].trim();
                            String inventor = bookElements[2].trim();
                            String type = bookElements[3].trim();
                            // boolean flag for if current spellbook is found in system
                            boolean foundInSystem = false;
                            // check if spellbook already exists in system
                            for (SpellBook sb : SpellBook.getAllBooks()) {
                                if (sb.getSerialNumber().equals(serialNumber)) {
                                    foundInSystem = true;
                                    break;
                                }
                            }
                            // if spellbook exists:
                            if (foundInSystem) continue;
                            // create new spellbook
                            new SpellBook(serialNumber, bookName, inventor, type);
                            // increment book add counter
                            booksFound++;
                        }
                        fileScan.close();
                        // if no books added from file:
                        if(booksFound == 0){
                            System.out.println("No spellbooks have been added to the system.\n");
                            continue;
                        }
                        // if more than 1 book is added:
                        System.out.println(booksFound + " spellbooks successfully added.\n");
                    // if file is not found:
                    } catch (FileNotFoundException e) {
                        System.out.println("No such collection.\n");
                    }
                }

                // ADD STUDENT <name>
                if (inputsUpper[1].equals("STUDENT")) {
                    String studentName = null;
                    // if student has surname, combine into a single string
                    if(inputsUpper.length == 3){
                        studentName = inputs[2];
                    } else if(inputsUpper.length == 4){
                        studentName = inputs[2] + " " + inputs[3];
                    }
                    // create new student object
                    new Student(studentName);
                    System.out.println("Success.\n");
                }
            }

            // SAVE COLLECTION <filename>
            if (inputsUpper[0].equals("SAVE") && inputsUpper.length == 3 && inputsUpper[1].equals("COLLECTION")) {
                // check if spellbooks exist
                if (SpellBook.getAllBooks().isEmpty()) {
                    System.out.println("No spellbooks in system.\n");
                    continue;
                }
                // attempt to read file
                try {
                    String filename = inputs[2].trim();
                    // if input file name doesnt have file extension .csv, add to string
                    if (!filename.endsWith(".csv")) filename += ".csv";
                    FileWriter savedCollection = new FileWriter(filename);
                    // add header to top of file
                    savedCollection.write("serialNumber,title,inventor,type\n");
                    // add each spellbook in the current list of all books to file
                    for (SpellBook spellbook : SpellBook.getAllBooks()) {
                        savedCollection.write(spellbook.getSerialNumber() + "," + spellbook.getName() + "," + spellbook.getInventor() + "," + spellbook.getType() + "\n");
                    }
                    savedCollection.close();
                    System.out.println("Success.\n");
                // if file is not found:
                } catch (FileNotFoundException f) {
                    System.out.println("Invalid file path.\n");
                // if any other in outputting the file:
                } catch (IOException e) {
                    System.out.println("Error writing to disk.\n");
                }
            }

            // COMMON <studentNumber1> <studentNumber2> ...
            if (inputsUpper[0].equals("COMMON") && inputs.length >= 3) {
                // check if students exist
                if (Student.getAllStudents().isEmpty()) {
                    System.out.println("No students in system.\n");
                    continue;
                }
                // check if spellbooks exist
                if (SpellBook.getAllBooks() == null || SpellBook.getAllBooks().isEmpty()) {
                    System.out.println("No spellbooks in system.\n");
                    continue;
                }
                // check duplicate students from inputs
                Set<String> inputStudents = new HashSet<>();
                // flag for duplicate found
                boolean duplicateFound = false;
                // for each student given, check if it exists in the list of all added students
                for (int i = 1; i < inputs.length; i++) {
                    if (inputStudents.contains(inputs[i])) {
                        System.out.println("Duplicate students provided.\n");
                        duplicateFound = true;
                        break;
                    }
                    // add student to list of all added students
                    inputStudents.add(inputs[i]);
                }
                // if duplicate student inputs are found:
                if(duplicateFound) continue;
                // list to store a set of histories for each input student
                List<Set<String>> studentHistories = new ArrayList<>();
                // flag for student found
                boolean studentFound = true;
                // for each input student...
                for (int i = 1; i < inputs.length; i++) {
                    String studentNumber = inputs[i];
                    Student target = null;
                    // check that student exists in system
                    for (Student s : Student.getAllStudents()) {
                        if (s.getStudentNumber().equals(studentNumber)) {
                            target = s;
                            break;
                        }
                    }
                    // if student doesn't exist:
                    if (target == null) {
                        System.out.println("No such student in system.\n");
                        studentFound = false;
                        break;
                    }
                    // initialise set of student's history to add into list
                    Set<String> history = new HashSet<>();
                    // add student's history to set
                    for (SpellBook b : target.getRentHistory()) history.add(b.shortString());
                    // add this set into the complete list of each student's history
                    studentHistories.add(history);
                }
                // if student is not found:
                if(!studentFound) continue;
                // create set to store all common books between students
                Set<String> commonBooks = new HashSet<>();
                // make sure there are student histories to compare
                if (!studentHistories.isEmpty()) {
                    // first add all books from the history of the first student into the set
                    commonBooks.addAll(studentHistories.get(0));
                    // for each set of student histories in the histories list, keep only the elements which
                    // are shared between it and the first student's history
                    for (int i = 1; i < studentHistories.size(); i++) commonBooks.retainAll(studentHistories.get(i));
                }
                // if set of all common books is empty, no books are common
                if (commonBooks.isEmpty()) {
                    System.out.println("No common spellbooks.\n");
                    continue;
                }
                // convert the set to a string so it can be ordered
                List<String> out = new ArrayList<>(commonBooks);
                // sort list alphabetically
                Collections.sort(out);
                // print out each common book in the sorted list
                for (String s : out) System.out.println(s);
                System.out.println();
            }
        }
        scan.close();
        // print exit message when loop ends via EXIT command
        System.out.println("Ending Archive process.");
    }
}
