package com.tau.project.models;
import java.util.Date;
<<<<<<< HEAD
import java.io.Serializable;
=======
>>>>>>> ec9629f7f2f94d741d158ae74166ffce178b857d
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
<<<<<<< HEAD
public class Project implements Serializable{
=======
public class Project {
>>>>>>> ec9629f7f2f94d741d158ae74166ffce178b857d
    @Id
    private Long project_id;
    private Long owner_id;
    private String project_title, project_description, status;
    private Date start_date, end_date;
    private ArrayList<String> tasks, project_programming_languages, project_preference_tags;
    private Double price;

}
