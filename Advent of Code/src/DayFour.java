import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DayFour {
    public static void main(String[] args) throws IOException {
        File file = new File("DayFourInput");
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<ArrayList<String>> passports = new ArrayList<>();


        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }

            var passportList = new ArrayList<String>();

            while (!line.equals("")) {
                var passport = Arrays.asList(line.split(" "));
                passportList.addAll(passport);
                line = br.readLine();
                if (line == null) {
                    break;
                }

            }
            passports.add(passportList);
        }

        ArrayList<ArrayList<String>> prelimPassports = new ArrayList<>();

        for (int i = 0; i < passports.size(); i++) {
            if (passports.get(i).size() == 8) {
                prelimPassports.add(passports.get(i));
            }
            else if (passports.get(i).size() == 7) {
                var valid = true;
                for (int j = 0; j < passports.get(i).size(); j++) {
                    if (passports.get(i).get(j).contains("cid")) {
                        valid = false;
                    }
                }
                if (valid) { prelimPassports.add(passports.get(i)); }
            }
        }


        ArrayList<Passport> finalPassports = new ArrayList<>();

        for (ArrayList<String> prelimPassport : prelimPassports) {
            finalPassports.add(new Passport(prelimPassport));
        }

        int validPassports = 0;
        for (Passport p : finalPassports) {
            if (p.checkValidity()) { validPassports++; }
        }
        System.out.println(validPassports);
    }
}

class Passport {

    private int birthYear;
    private int issueYear;
    private int expirationYear;
    private int height;
    private String heightUnit;
    private String hairColor;
    private String eyeColor;
    private String passportID;

    public Passport(ArrayList<String> passportString) {
        for(String s : passportString) {
            String[] splitted = s.split(":");

            switch (splitted[0]) {
                case "byr" -> birthYear = Integer.parseInt(splitted[1]);
                case "iyr" -> issueYear = Integer.parseInt(splitted[1]);
                case "eyr" -> expirationYear = Integer.parseInt(splitted[1]);
                case "hgt" -> {
                    String actualHeight = splitted[1];
                    actualHeight = actualHeight.replaceAll("[^\\d.]", "");
                    height = Integer.parseInt(actualHeight);
                    if (splitted[1].contains("cm")) {
                        heightUnit = "cm";
                    } else if (splitted[1].contains("in")) {
                        heightUnit = "in";
                    }
                }
                case "hcl" -> hairColor = splitted[1];
                case "ecl" -> eyeColor = splitted[1];
                case "pid" -> passportID = splitted[1];
            }
        }
    }

    public boolean checkValidity() {

        boolean valid = checkBirthYear() &&
                checkIssueYear() &&
                checkExpirationYear() &&
                checkHeight() &&
                checkHairColor() &&
                checkEyeColor() &&
                checkPassportID();

        System.out.println("VALID - " + valid);
        System.out.println("");
        System.out.println("");

        return valid;
    }

    public boolean checkBirthYear() {
        if (birthYear == 0) { return false; }
        System.out.println("Birth Year- " + birthYear);
        return (birthYear <= 2002 && birthYear >= 1920);
    }
    public boolean checkIssueYear() {
        if (issueYear == 0) { return false; }
        System.out.println("Issue Year- " + issueYear);
        return (issueYear <= 2020 && issueYear >= 2010);
    }
    public boolean checkExpirationYear() {
        if (expirationYear == 0) { return false; }
        System.out.println("Expiration Year- " + expirationYear);
        return (expirationYear <= 2030 && expirationYear >= 2020);
    }
    public boolean checkHeight() {
        if (height == 0 || heightUnit == null) { return false; }
        System.out.println("Height- " + height);
        System.out.println("Height Unit- " + heightUnit);
        if (heightUnit.equals("cm")) {
            return (height <= 193 && height >= 150);
        }
        else if (heightUnit.equals("in")) {
            return (height <= 76 && height >= 59);
        }
        return true;
    }
    public boolean checkHairColor() {
        if (hairColor == null) { return false; }
        System.out.println("Hair Color- " + hairColor);

        if (hairColor.charAt(0) != '#' || hairColor.length() != 7) { return false; }

        var hairChars = hairColor.substring(1).toCharArray();

        for(Character c : hairChars) {
            if (!"abcdef1234567890".contains(Character.toString(c))) {
                return false;
            }
        }
        return true;

    }
    public boolean checkEyeColor() {
        if (eyeColor == null) { return false; }
        System.out.println("Eye Color- " + eyeColor);

        return eyeColor.equals("amb") || eyeColor.equals("blu") || eyeColor.equals("brn") || eyeColor.equals("gry") ||
                eyeColor.equals("grn") || eyeColor.equals("hzl") || eyeColor.equals("oth");
    }
    public boolean checkPassportID() {
        if (passportID == null) { return false; }
        System.out.println("Passport ID- " + passportID);

        return (passportID.length() == 9);
    }
}