package entity.users;

import enums.UserType;

public class Viewer implements UserRole {

    private static Viewer instance = null;
    private static UserType userType = UserType.VIEWER;

    // Private constructor ensures that the class cannot be instantiated
    private Viewer() { } 

    public static Viewer getInstance() {
        // if(instance == null) {
        //     synchronized (Viewer.class) {
        //         if(instance == null) {
        //             instance = new Viewer();
        //         }
        //     }
        // }
        if(instance == null) {
            instance = new Viewer();
        }
        return instance;
    }


    @Override
    public int getReviewFactor() {
        return 1;
    }

    @Override
    public UserRole upgrade() {
        return Critic.getInstance();
    }

    @Override
    public boolean isUpgradable(int moviesReviewedCount) {
        if(moviesReviewedCount >= 3)
            return true;
        else
            return false;
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

}