package Services;


import java.io.IOException;

class UserService {

    private static volatile UserService userService;
    private UserRepository userRepo;

    private UserService() {
        userRepo = UserRepository.getInstance();
    }

    public static UserService getInstance() {

        UserService result = userService;

        if (result == null) {
            synchronized (UserService.class) {
                result = userService;
                if (result == null) {
                    userService = result = new UserService();
                }
            }
        }
        return result;
    }

    boolean createUser(User user) {
        return true;
    }

    boolean updateEmail(User user, String updatedEmail) throws IOException {
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), updatedEmail, user.getName(), user.getPassword());
        updateData(newUser);
        return true;
    }

    boolean updateName(User user, String updatedName) throws IOException {
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), user.getEmail(), updatedName, user.getPassword());
        updateData(newUser);
        return true;
    }

    boolean updatePassword(User user, String updatedPassword) throws IOException {
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), user.getEmail(), user.getName(), updatedPassword);
        updateData(newUser);
        return true;
    }

    boolean deleteUser(User user) {
        userRepo.deleteFile(user);
        return true;
    }


    void updateData(User user) throws IOException {
        userRepo.writeToFile(user.getEmail() + ".json", user);
    }
}
