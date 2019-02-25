package com.vanpool.vanpool.DAO;

import java.util.Map;

public class UserDAO
{
    private static Map<Integer, User> users;

    static {
        users = new HashMap<Integer, User>() {
            {
                put(1, new User(1234, "Alan", "1234"));
                put(2, new User(1235, "Amanda", "1235"));
                put(3, new User(1236, "Luke", "1236"));
            }
        };

    }

    public Collection<User> getAllDrivers() {
        return this.users.values();
    }

    public void updateDriverByID(User user)
    {
        User updaterUser = users.get(users.getId());
        updaterUser.setName(users.getName());

    }

    public User getUserProfile(String username, String password)
    {
        User userProfile=users.get(username);
        if (userProfile.getPassword()==password)
        {
            return userProfile;
        }
        else
        {
            return null;
        }

    }
}
