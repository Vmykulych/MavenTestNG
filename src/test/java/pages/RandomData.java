package pages;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomData {
    private final WebDriver driver;

    public RandomData (WebDriver driver) {
        this.driver=driver;
    }

    public String getRandomPassword() {
        String characters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz!@#$%^&*()-_?123456789";
        String randomString = "";
        int length = 10;

        Random randPass = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(randPass.nextInt(characters.length()));
        }
        for (int i = 0; i < text.length; i++) {
            randomString += text[i];
        }
        System.out.println("Random password is:" + randomString);
        return randomString;
    }

    public static String generateUniqueStringOfLettersUsingCurrentDate(String prefix, String suffix) {
        DateTimeFormatter dateFormatterNew = DateTimeFormatter.ofPattern("SssmmhhddMMyyyy");
        String uniqueDate = dateFormatterNew.format(LocalDateTime.now());
        String result = "";
        List<Character> listOfChar = uniqueDate.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

        // changing each number from date into character starting from A
        for (char ch : listOfChar) {
            result += (char) (ch + 49);
        }

        return prefix + result + suffix;
    }


}
