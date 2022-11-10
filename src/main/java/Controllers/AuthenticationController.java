package Controllers;

import Services.AuthenticationService;

public class AuthenticationController {
     AuthenticationService authService;

    public AuthenticationController() {
        this.authService = AuthenticationService.getInstance();
    }

    public String login(String email, String password) {
        Utils.checkEmail(email);
        Utils.checkPassword(password);
        return authService.login(email, password);
    }

    public void register(String email, String name, String password) {
        Utils.checkEmail(email);
        Utils.checkName(name);
        Utils.checkPassword(password);
        authService.register(email, name, password);
    }
}

