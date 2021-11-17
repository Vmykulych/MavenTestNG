package pages;
import org.openqa.selenium.WebDriver;

import java.util.Random;

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


}
