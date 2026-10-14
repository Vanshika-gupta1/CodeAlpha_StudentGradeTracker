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
    @Override
    public String toString(){
        return String.format("%s (avg: %.2f, highest: %.2f, lowest: %.2f)",
                name, getAverage(), getHighest(), getLowest());
    }

    public String toFileLine(){
        StringBuilder sb = new StringBuilder(name);
        for(Double m : marks){
            sb.append(",");
            sb.append(m);
        }
        return sb.toString();
    }

    public static Student fromFileLine(String line){
        String[] parts = line.split(",");
        String name = parts[0];
        ArrayList<Double> marks = new ArrayList<>();
        for(int i=1;i< parts.length;i++){
            double checkmark = Double.parseDouble(parts[i].trim());
            marks.add(checkmark);
        }
        return new Student(name,marks);
    }
}
