package Utils;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public class Utils {
    static void checkEmail(String temp) {
        if (temp == null) throw new InvalidParameterException("String empty");
        boolean matches = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$").matcher(temp).matches();
        if (!matches) throw new InvalidParameterException("Email not in correct format");
    }

    static void checkPassword(String pass) {
        if (pass == null || pass.equals("")) throw new InvalidParameterException("Password not valid");
    }

    static void checkName(String name) {
        if (name.length() > 10) throw new InvalidParameterException();
    }

    static boolean isJsonFile(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".")).equals(".json");
    }
}
