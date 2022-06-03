package com.tau.project.models;

import lombok.*;
import javax.persistence.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Users {
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
    private Long user_id;
}
