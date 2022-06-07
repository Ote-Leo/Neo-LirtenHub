package com.tau.user.models;


import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Setter
@Getter 
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document("User Authentication")

public class UserAuth {
    @Id
    private Long id; //unique id for the record of the login
    private Long user_id;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
  
}
