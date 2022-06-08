package com.tau.project.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document("Project_Applicant")
public class Project_Applicant {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompositeKey implements Serializable {
        private Long user_id;
        private Long project_id;
    }

    @Id
    private CompositeKey id;
    private boolean isAccepted;
    private boolean isFinished;
    private boolean isPaid;
    
}
