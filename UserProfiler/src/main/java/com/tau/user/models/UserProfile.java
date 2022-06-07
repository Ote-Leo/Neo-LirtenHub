package com.tau.user.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Setter
@Getter 
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document("User Profile")
public class UserProfile {
    @Id
    private Long id;
    private String firstName, lastName, email, gitHubLink, bio_section, password, location;
    private ArrayList<String> projectPreferenceTags, interests, codingLanguages;
    private ArrayList<Long> blocked_users;
    private ArrayList<Report> reported_users; 
    private UserRole userRole; 
}
