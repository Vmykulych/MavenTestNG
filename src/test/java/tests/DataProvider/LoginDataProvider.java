package tests.DataProvider;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name="EmptyPasswordLogin")
    public static Object[][] getEmptyPasswordFieldErrorMessage(){
        return new Object[][] {
                {"vmykulych@visionps.com", "The Password field is required."},
                };
        };


@DataProvider(name="InvalidEmail")
    public static Object [][] getIncorrectEmailErrorMessage() {
             return new Object[][] {
                {"vmykulychvisionps.com", "Vision124!/Z", "The sign in information you provided was incorrect."},
                {"vmykulych@visionpscom", "Vision124!/Z", "The sign in information you provided was incorrect."},
                {"vmykulych@visionps.co", "Vision124!/Z", "The sign in information you provided was incorrect."},
                {"vvmykulych@visionps.com", "Vision124!/Z", "The sign in information you provided was incorrect."}

    };

    }
}

