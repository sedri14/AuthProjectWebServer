package Controllers;

import Services.AuthenticationService;

import java.io.IOException;
import java.security.InvalidParameterException;

public class UserController {

    AuthenticationService authService;
    UserService userService;

    public UserController() {
        this.authService = AuthenticationService.getInstance();
        this.userService = UserService.getInstance();
    }

    public boolean updateEmail(String mail, String token) throws IOException {
        try{
            Utils.checkEmail(mail);
        }catch (InvalidParameterException ip){
            throw new InvalidParameterException("Email not in correct format");
        }
        User user = authService.validate(token);
        boolean status = userService.updateEmail(user, mail);
        if (status) {
            authService.reloadUser(mail, token);
        }
        return status;
    }

    public boolean updateName(String name, String token) throws IOException {
        try{
            Utils.checkName(name);
        }catch (InvalidParameterException ip){
            throw new InvalidParameterException("Name not in correct format");
        }
        User user = authService.validate(token);
        boolean status = userService.updateName(user, name);
        if (status) {
            authService.reloadUser(user.getEmail(), token);
        }
        return status;
    }
    public boolean updatePassword(String password, String token) throws IOException {
        try{
            Utils.checkPassword(password);
        }catch (InvalidParameterException ip){
            throw new InvalidParameterException("Email not in correct format");
        }
        User user = authService.validate(token);
        boolean status = userService.updatePassword(user, password);
        if (status) {
            authService.reloadUser(user.getEmail(), token);
        }
        return status;
    }

    public boolean deleteUser(String token){
        User user = authService.validate(token);
        boolean status = userService.deleteUser(user);
        if (status) {
            authService.removeToken(token);
        }
        return status;
    }

}

