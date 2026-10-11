import java.util.ArrayList;

public class GradeManager {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s){
        students.add(s);
    }

    public Student findByName(String name){
        for(Student s : students){
            if(s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    public boolean removeStudent(String name){
        Student found = findByName(name);
        if(found == null) return false;
        students.remove(found);
        return true;
    }

    public double getClassAvg(){
        if(students.isEmpty()) return 0;
        double sum = 0;
        for(Student s : students){
            sum += s.getAverage();
        }
        return sum / students.size();
    }

    public Student getTopper(){
        if(students.isEmpty()) return null;
        Student best = students.getFirst();
        for(Student s : students){
            if(s.getAverage() > best.getAverage())
                best = s;
        }
        return best;
    }

    public Student getLowestScorer(){
        if(students.isEmpty()) return null;
        Student lowest = students.getFirst();
        for(Student s : students){
            if(s.getAverage() < lowest.getAverage())
                lowest = s;
        }
        return lowest;
    }

    public void printReport(){
        if(students.isEmpty()) {
            System.out.println("No students to display");
            return ;
        }
        System.out.printf("%-15s %10s %10s %10s%n", "Name", "Average", "Highest", "Lowest");
        System.out.println("-".repeat(48));
        for (Student s : students) {
            System.out.printf("%-15s %10.2f %10.2f %10.2f%n",
                    s.getName(), s.getAverage(), s.getHighest(), s.getLowest());
        }
        System.out.println("-".repeat(48));
        System.out.printf("%-15s %10.2f%n", "Class Average:", getClassAvg());
    }
}
