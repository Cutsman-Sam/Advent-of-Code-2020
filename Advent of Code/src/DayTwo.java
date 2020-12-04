import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayTwo {
    public static void main(String[] args) throws IOException {
        File file = new File("DayTwoInput");
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<String[]> passwords = new ArrayList<>();

        while(true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }

            line = line.replace(":", "");
            String[] password = line.split(" ");
            passwords.add(password);
        }


        System.out.println(part2(passwords));
    }

    public static int part1(ArrayList<String[]> passwords) {
        int validPasswords = 0;
        for (String[] password : passwords) {
            char letter = password[1].charAt(0);

            String[] minmax = password[0].split("-");
            int min = Integer.parseInt(minmax[0]);
            int max = Integer.parseInt(minmax[1]);
            int charsMatching = 0;

            char[] stringToCheck = password[2].toCharArray();

            for (char c : stringToCheck) {
                if (c == letter) {
                    charsMatching++;
                }
            }
            if (charsMatching <= max && charsMatching >= min) {
                validPasswords++;
            }
        }
        return validPasswords;
    }

    public static int part2(ArrayList<String[]> passwords) {
        int validPasswords = 0;
        for (String[] password : passwords) {
            char letter = password[1].charAt(0);

            String[] minmax = password[0].split("-");
            int pos1 = Integer.parseInt(minmax[0]) - 1;
            int pos2 = Integer.parseInt(minmax[1]) - 1;

            boolean pos1Valid = false;
            boolean pos2Valid = false;

            char[] stringToCheck = password[2].toCharArray();

            for (int i = 0; i < stringToCheck.length; i++) {
                if (i == pos1) {
                    if (stringToCheck[i] == letter) {
                        pos1Valid = true;
                    }
                }
                if (i == pos2) {
                    if (stringToCheck[i] == letter) {
                        pos2Valid = true;
                    }
                }
            }

            if (pos1Valid ^ pos2Valid) {
                validPasswords++;
            }
        }
        return validPasswords;
    }
}
