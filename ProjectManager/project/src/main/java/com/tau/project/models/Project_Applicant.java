package com.tau.project.models;

import lombok.*;
import javax.persistence.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project_Applicant {
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
    private Long project_applicant_id;
    private Long user_id;
    private Long project_id;
    private boolean isAccepted;
   
    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    } 
}
