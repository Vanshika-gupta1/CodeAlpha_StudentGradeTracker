import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Double> marks;

    public Student(String name, ArrayList<Double> marks) {
        this.name = name;
        this.marks = new ArrayList<>(marks);
    }

    public String getName() { return name; }

    public double getAverage(){
        if(marks.isEmpty()) return 0;
        else{
            double sum = 0;
            for(double m : marks){
                sum += m;
            }
            return sum / marks.size();
        }
    }

    public double getHighest(){
        if(marks.isEmpty()) return 0;
        else {
            double highest = marks.getFirst();
            for(double m : marks){
                if(m > highest) highest = m;
            } return highest;
        }
    }

    public double getLowest(){
        if(marks.isEmpty()) return 0;
        else {
            double lowest = marks.getFirst();
            for(double m : marks){
                if(m < lowest) lowest = m;
            } return lowest;
        }
    }
}
