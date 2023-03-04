package entity.users;

import enums.UserType;

public class Critic implements UserRole {

    private static Critic instance = null;
    private static UserType userType = UserType.CRITIC;

    private Critic() { }

    public static Critic getInstance() {
        // if(instance == null) {
        //     synchronized (Critic.class) {
        //         if(instance == null) {
        //             instance = new Critic();
        //         }
        //     }
        // }
        if(instance == null) {
            instance = new Critic();
        }
        return instance;
    }


    @Override
    public int getReviewFactor() {
        return 2;
    }

    @Override
    public UserRole upgrade() {
        // Critic can be further upgraded to EXPERT role, not yet implemented
        System.out.println("Critic update feature not available yet!");
        return null;
    }

    @Override
    public boolean isUpgradable(int moviesReviewedCount) {
        System.out.println("Critic isUpgradable feature not available yet!");
        return false;
    }

    @Override
    public UserType getUserType() {
        return userType;
    }
}