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
//        for(Student s : students){
//            if(s.getName().equalsIgnoreCase(name)) {
//                students.remove(s);
//                return true;
//            }
//        } return false;
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
}
