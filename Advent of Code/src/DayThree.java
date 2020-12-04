import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayThree {
    public static void main(String[] args) throws IOException {
        File file = new File("DayThreeInput");
        BufferedReader br = new BufferedReader(new FileReader(file));

        char[][] map = new char[323][];

        int row = 0;

        while(true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }

            char[] mapLine = line.toCharArray();
            map[row] = mapLine;

            row++;

        }


        int x_index = 0;
        int y_index = 0;

        int x_increment = 1;
        int y_increment = 2;

        int trees = 0;

        while (y_index < 322) {
            x_index += x_increment;
            if (x_index >= 31) x_index -= 31;
            y_index += y_increment;

            if (map[y_index][x_index] == '#') {
                trees++;
            }
        }

        System.out.println(trees);
    }
}
