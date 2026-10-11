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

}
