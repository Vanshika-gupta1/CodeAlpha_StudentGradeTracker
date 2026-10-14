import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class GradeManager {
    private ArrayList<Student> studentsList = new ArrayList<>();

    public void addStudent(Student s){
        studentsList.add(s);
    }

    public Student findByName(String name){
        for(Student s : studentsList){
            if(s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    public boolean removeStudent(String name){
        Student found = findByName(name);
        if(found == null) return false;
        studentsList.remove(found);
        return true;
    }

    public double getClassAvg(){
        if(studentsList.isEmpty()) return 0;
        double sum = 0;
        for(Student s : studentsList){
            sum += s.getAverage();
        }
        return sum / studentsList.size();
    }

    public Student getTopper(){
        if(studentsList.isEmpty()) return null;
        Student best = studentsList.getFirst();
        for(Student s : studentsList){
            if(s.getAverage() > best.getAverage())
                best = s;
        }
        return best;
    }

    public Student getLowestScorer(){
        if(studentsList.isEmpty()) return null;
        Student lowest = studentsList.getFirst();
        for(Student s : studentsList){
            if(s.getAverage() < lowest.getAverage())
                lowest = s;
        }
        return lowest;
    }

    public void printReport(){
        if(studentsList.isEmpty()) {
            System.out.println("No students to display");
            return ;
        }
        System.out.printf("%-15s %10s %10s %10s%n", "Name", "Average", "Highest", "Lowest");
        System.out.println("-".repeat(48));
        for (Student s : studentsList) {
            System.out.printf("%-15s %10.2f %10.2f %10.2f%n",
                    s.getName(), s.getAverage(), s.getHighest(), s.getLowest());
        }
        System.out.println("-".repeat(48));
        System.out.printf("%-15s %10.2f%n", "Class Average:", getClassAvg());
    }

    public boolean saveToFile(String filename,int noOfSub){
        StringBuilder sb = new StringBuilder();
        sb.append(noOfSub).append("\n");
        for(Student s : studentsList){
            sb.append(s.toFileLine()).append("\n");
        }
        Path temp = Path.of(filename + ".tmp");
        Path path = Path.of(filename);
        try{
            Files.writeString(temp,sb.toString());
            Files.move(temp,path, StandardCopyOption.ATOMIC_MOVE);
            return true;
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
            return false;
        }
    }

    public int loadFromFile(String filename){
        Path path = Path.of(filename);
        if(!Files.exists(path)) return -1;
        try{
            List<String> lines = Files.readAllLines(path);
            if(lines.isEmpty()) return -1;
            int noOfSub = Integer.parseInt(lines.get(0).trim());
            for(int i=1 ; i<lines.size() ; i++){
                if(lines.get(i).isBlank()) continue;
                studentsList.add(Student.fromFileLine(lines.get(i)));
            }
            return noOfSub;
        }
        catch (IOException | NumberFormatException e) {
            System.out.println("datafile is unreadable , starting fresh");
            studentsList.clear();
            return -1;
        }
    }
}
