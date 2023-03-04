package manager.usermanager;

import java.util.ArrayList;
import java.util.List;

import entity.users.User;

import java.util.HashMap;

public class UserManager {

    private HashMap<String, User> userMap;

    public UserManager() {
        userMap = new HashMap<>();
    }

    public void addUser(User user) {
        userMap.putIfAbsent(user.getName(), user);
        // String userName = user.getName();
        // if(userMap.containsKey(userName)) {
        //     System.out.println("Looks like the user with the same name exists already!");
        // } else {
        //     userMap.put(userName, user);
        // }
    }

    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }

    public User getUser(String userName) {
        return userMap.get(userName);
    }
}
