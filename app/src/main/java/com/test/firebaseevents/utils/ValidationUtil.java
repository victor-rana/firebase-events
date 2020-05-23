package com.test.firebaseevents.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ashish
 */
public class ValidationUtil {
    private final static String NAME_PATTERN = "^[a-zA-ZйьцкеБЕЙб .ґ'`-].{1,50}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,15}$";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";
    private static final String TITLE_PATTERN = "^[a-zA-Z0-9_. ']{2,100}$";
    private static final String TITLE_PATTERN_ARRABIC = " ^[\\u0621-\\u064A\\u0660-\\u0669 ]+$";
    public static final String pan_pattern = "(([A-Za-z]{5})([0-9]{4})([a-zA-Z]))";

    /**
     * Validates email with regular expression
     *
     * @param email the email to validate
     * @return true if email is valid, false otherwise
     */
    public static boolean isValidEmailAddress(String email) {

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email.toLowerCase());

        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        email = email.trim();
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email.toLowerCase());

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    /**
     * Validates user name with regular expression
     *
     * @param username the username to validate
     * @return true if user name is valid, false otherwise
     */
    public static boolean isValidUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }

    public static boolean isValidTitlePattern(String username, String lan) {
        String pattern_s;


       /* if (lan.equalsIgnoreCase("ar")) {
            //pattern_s = TITLE_PATTERN_ARRABIC;
            return true;
        } else {
            pattern_s = TITLE_PATTERN;
        }*/
        pattern_s = TITLE_PATTERN;

        Pattern pattern = Pattern.compile(pattern_s);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }

    public static boolean regex_matcher(Pattern pattern, String string) {
        Matcher m = pattern.matcher(string);
        return m.find() && (m.group(0) != null);
    }

    public static boolean isValidString(String username, int limit) {
        return (username != null && username.length() >= limit) ? true : false;
    }

    public static boolean isValidFirstName(String firstname) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(firstname);
        return matcher.matches();
    }

    /**
     * Validate password with regular expression
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public static boolean isValidPassword(final String password) {

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }

    public static boolean isValidPanNumber(final String panNumber) {

        Pattern pattern = Pattern.compile(pan_pattern);
        Matcher matcher = pattern.matcher(panNumber);
        return matcher.matches();

    }


}
