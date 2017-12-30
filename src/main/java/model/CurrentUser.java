package main.java.model;

/**
 * Created by User on 26.11.2017.
 */
public class CurrentUser {

    private static User user;

    public static User getUser() {
        return user;
    }

    public static boolean isCurrentUserAdmin(){
        return (user.getRole().equals("admin")) ? true : false;
    }

    public static void setUser(User luser) {
        user = luser;
    }
}
