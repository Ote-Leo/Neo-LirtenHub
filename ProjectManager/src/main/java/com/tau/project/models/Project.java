package com.tau.project.models;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document("Project")
public class Project {
    @Id
    private Long project_id;
    private Long owner_id;
    private String project_title, project_description, status;
    private Date start_date, end_date;
    private ArrayList<String> tasks, project_programming_languages, project_preference_tags;
    private Double price;
    
}

