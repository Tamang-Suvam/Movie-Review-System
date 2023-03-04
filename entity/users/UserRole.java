package entity.users;

import enums.UserType;

public interface UserRole {

    int getReviewFactor();

    UserRole upgrade();

    boolean isUpgradable(int moviesReviewedCount);

    public UserType getUserType();

}