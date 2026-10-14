import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int readInt(Scanner sc , String prompt){
        while(true){
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try{
                return Integer.parseInt(line);
            } catch(NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    public static String readName(Scanner sc, GradeManager g) {
        while(true){
            System.out.print("Enter Student Name : ");
            String name = sc.nextLine().trim();
            if (name.isBlank()) {
                System.out.println("Name cannot be empty");
            }
            else if (name.contains(",")) {
                System.out.println("Name cannot contain commas.");
            }
            else if (name.length() > 15) {
                System.out.println("Name must be 15 characters or fewer.");
            }
            else if(g.findByName(name) != null){
                System.out.println("Student already exists, don't add a duplicate name,use other name");
            }
            else {
                return name;
            }
        }
    }

    public static void fillMarks(ArrayList<Double> mark , int noOfSub , Scanner sc){
        for(int i = 1 ; i <= noOfSub ; i++){
            String prompt = "Enter marks of subject " + i + " : ";
            double markOfOneSub = readDouble(prompt,sc);
            mark.add(markOfOneSub);
        }
    }

    public static double readDouble(String prompt , Scanner sc){
        while(true){
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try{
                double value = Double.parseDouble(line);
                if (!(value >= 0 && value <= 100)) { // to trigger NaN
                    System.out.println("Marks must be between 0 and 100."); }
                else return value;
            } catch (NumberFormatException e){
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }

    public static void main(String[] args) {

        final String fileName = "students.txt";

        GradeManager gradeManager = new GradeManager();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        int noOfSub;

        System.out.println("************* Welcome *************\n");

        noOfSub = gradeManager.loadFromFile(fileName);
        if(noOfSub == -1){
            while(true){
                noOfSub = readInt(sc, "Enter number of subjects : ");
                if (noOfSub >= 1 && noOfSub <= 20) break;
                System.out.println("Number of subjects must be between 1 and 20.");
            }
        } else {
            System.out.println("Loaded data from file");
            System.out.println("no of subject = "+noOfSub);
        }
        System.out.println();

        while(running){

            System.out.println("===== Student Grade Tracker =====");
            System.out.print("1. Add student\n" +
                            "2. View report\n" +
                            "3. Search student\n" +
                            "4. Delete student\n" +
                            "5. Show topper and lowest scorer\n" +
                            "6. Exit\n");
            int choice = readInt(sc , "Enter choice : ");

            switch(choice){
                case 1 : {
                    String name = readName(sc, gradeManager);
                    ArrayList<Double> mark = new ArrayList<>();
                    fillMarks(mark, noOfSub, sc);
                    Student student = new Student(name, mark);
                    gradeManager.addStudent(student);
                    System.out.println("Added student " + name + " successfully.");
                    break;
                }
                case 2: {
                    System.out.println("Student report");
                    gradeManager.printReport();
                    break;
                }
                case 3: {
                    System.out.print("Enter student name to search : ");
                    String name = sc.nextLine().trim();
                    Student s = gradeManager.findByName(name);
                    if(s == null) {
                        System.out.println("Student with name " + name + " is not present in list");
                    } else {
                        System.out.println("Student with name " + name + " is present in list");
                        System.out.println(s);
                    }
                    break;
                }
                case 4 : {
                    System.out.print("Enter student name to delete : ");
                    String name = sc.nextLine().trim();
                    boolean studentPresent = gradeManager.removeStudent(name);
                    if(studentPresent) {
                        System.out.println("Removed student " + name + " from list.");
                    } else{
                        System.out.println("Student with name " + name + " is not present in list");
                    }
                    break;
                }
                case 5 : {
                    System.out.println("Show topper and lowest scorer");
                    Student s = gradeManager.getTopper();
                    if(s == null) {
                        System.out.println("No students yet");
                    } else {
                        System.out.println("Topper : " + s);
                        System.out.println("Lowest scorer : " + gradeManager.getLowestScorer());
                    }
                    break;
                }
                case 6 : {
                    if(gradeManager.saveToFile(fileName,noOfSub)) {
                        System.out.println("data saved");
                        running = false;
                    }
                    else System.out.println("Save failed. Your data is still in memory. Fix the problem and choose 6 again.");
                    break;
                }
                default:
                    System.out.println("Invalid choice try again");
            }
        }
        System.out.println("\nThank You !");
        sc.close();
    }
}
