package services;

import manager.usermanager.UserManager;
import entity.users.User;

public class UserService {

    private UserManager userManager;

    public UserService(UserManager userManager) {
        this.userManager = userManager;
    }

    public void addUser(String name) throws IllegalArgumentException {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("User name cannot be null or empty");
        if(isDuplicateUser(name))
            throw new IllegalArgumentException(String.format("User with name %s already exists", name));
        User user = new User(name);
        userManager.addUser(user);
    }

    private boolean isDuplicateUser(String name) {
        return getUser(name) != null;
    }

    public boolean isUserUpgradable(User user, int moviesReviewedCount) {
        return user.getRole().isUpgradable(moviesReviewedCount);
    }

    public void upgradeUser(User user) {
        user.setRole(user.getRole().upgrade());
    }

    public User getUser(String name) {
        return userManager.getUser(name);
    }
}