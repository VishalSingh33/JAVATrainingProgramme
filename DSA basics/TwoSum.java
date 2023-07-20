import java.io.*;
import java.util.*;

public class TwoSum {

    static String findLanguage(String country) {

        if ((userCountry.equals("COUNTRY_INDIA") && (userLanguage.equals("LANGUAGE_HINDI")
                || userLanguage.equals("LANGUAGE_ENGLISH")))
                || (userCountry.equals("COUNTRY_USA") && (userLanguage.equals("LANGUAGE_ENGLISH")
                        || userLanguage.equals("LANGUAGE_SPANISH")))) {

            userPreference.updateUserLanguage(userName, userCountry, userLanguage);
        } else {
            try {
                throw new Exception("Invalid country/language combination");
            } catch (Exception e) {
                e.printStackTrace();

            }
            // =================================================

            // if ((userCountry.equals("COUNTRY_INDIA") && userLanguage.equals("LANGUAGE_HINDI")
            // || userLanguage.equals("LANGUAGE_ENGLISH" ) )
            // ||
            // ( userCountry.equals("COUNTRY_USA") &&
            // userLanguage.equals("LANGUAGE_HINDI") ||
            // userLanguage.equals("LANGUAGE_ENGLISH" ) )
            // ){
            // userPreference.updateUserLanguage(userName, userLanguage);
            // }else{
            // try {
            // throw new Exception("Invalid country/language combination");
            // } catch (Exception e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }

        }
    }
}