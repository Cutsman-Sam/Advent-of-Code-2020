import java.io.*;
import java.util.ArrayList;

public class DayOne {
    public static void main(String[] args) throws IOException {
        File file = new File("DayOneNumbers");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<Integer> numbers = new ArrayList<>();

        while(true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            numbers.add(Integer.parseInt(line));
        }

        for(int i = 0; i < numbers.size(); i++) {
            for(int j = 0; j < numbers.size(); j++) {
                for(int k = 0; k < numbers.size(); k++) {
                    if (numbers.get(i) + numbers.get(j) + numbers.get(k) == 2020) {
                        System.out.println(numbers.get(i) * numbers.get(j) * numbers.get(k));
                    }
                }
            }
        }
    }
}
