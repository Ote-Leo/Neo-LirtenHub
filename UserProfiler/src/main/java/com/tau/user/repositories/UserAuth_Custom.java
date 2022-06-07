package com.tau.user.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.tau.user.models.UserAuth;

public interface UserAuth_Custom {
    public void updatelogoutTime(long user_id, LocalDateTime logout);
    public ArrayList<UserAuth> getUsers(long user_id);
}
