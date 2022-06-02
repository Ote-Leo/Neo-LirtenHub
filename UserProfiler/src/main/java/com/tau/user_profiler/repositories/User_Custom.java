package com.tau.user_profiler.repositories;

import java.util.ArrayList;

public interface User_Custom {
    public void updateGitHubLink(long user_id, String name);
    public void updatePreference(long user_id, String prefrence, String method);
    public ArrayList<String> getPreferences(Long user_id);
    public void updateInterest(long user_id, String interest, String method);
    public ArrayList<String> getInterests(Long user_id);
    public void updateBio(Long user_id, String Text_Section);
    public void updateBlock(Long user_id, Long blocked_id);
    public ArrayList<Long> getBlock(Long user_id);
}


