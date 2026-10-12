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
            } else {
                if (name.length() > 15) {
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

        GradeManager gradeManager = new GradeManager();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        int noOfSub;

        System.out.println("************* Welcome *************\n");

        do{
            noOfSub = readInt(sc , "Enter number of subjects : ");
            if(noOfSub < 1 || noOfSub > 20) System.out.println("Number of subject must be between 1 and 20");
            else break;
        } while(true);
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
                case 2:
                    System.out.println("View report");
                    break;
                case 3:
                    System.out.println("Search student");
                    break;
                case 4 :
                    System.out.println("Delete student");
                    break;
                case 5 :
                    System.out.println("Show topper and lowest scorer");
                    break;
                case 6 :
                    System.out.println("Exit");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice try again");
            }
        }
        System.out.println("\nThank You !");
    }

}
