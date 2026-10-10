import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> marks = new ArrayList<>(List.of(80.0,90.0,100.0));
        Student s = new Student("first_test" , marks);
        System.out.println("name is " + s.getName() + " highest marks is " + s.getHighest() +
                " and lowest marks is "+ s.getLowest() + " and average marks is " + s.getAverage());
    }
}
