package app.modules.user;

public interface UserModificationPort {

    void saveNewUser(User user) throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;

}
