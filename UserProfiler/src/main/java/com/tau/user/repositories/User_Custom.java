package com.tau.user.repositories;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.tau.user.models.Report;

public interface User_Custom {
    public void updateGitHubLink(long user_id, String name);
    public void updatePreference(long user_id, String prefrence, String method);
    public ArrayList<String> getPreferences(Long user_id);
    public void updateInterest(long user_id, String interest, String method);
    public ArrayList<String> getInterests(Long user_id);
    public void updateBio(Long user_id, String Text_Section);
    public void updateBlock(Long user_id, Long blocked_id);
    public ArrayList<Long> getBlock(Long user_id);  
    public void updateFirstName(Long user_id, String first_name);
    public void updateLastName(Long user_id, String last_name);
    public void updatePassword(Long user_id, String password) throws NoSuchAlgorithmException;
    public ArrayList<Report> getReport(Long user_id);  
    public void updateReport(Long user_id, Long reported_id, String report_description);
    public void updateLanguage(long user_id, String coding_language);
    public ArrayList<String> getLanguages(Long user_id);
    public void updateLocation(long user_id, String city_name);

}


