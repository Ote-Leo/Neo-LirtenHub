package com.tau.user.repositories;

import java.time.LocalDateTime;

public interface UserAuth_Custom {
    public void updatelogoutTime(long user_id, LocalDateTime logout);
}
