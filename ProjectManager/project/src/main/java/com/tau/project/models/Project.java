package com.tau.project.models;
import java.sql.Date;
import java.util.ArrayList;

import lombok.*;
import javax.persistence.*;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    private Long project_id;
    private Long owner_id;
    private String project_title;
    private String project_description;
    private Date start_date;
    private Date end_date;
    private ArrayList<String> tasks;
    private Double price;
    private ArrayList<String> project_programming_languages;
    private ArrayList<String> project_preference_tags;
    private String status;
    
}
