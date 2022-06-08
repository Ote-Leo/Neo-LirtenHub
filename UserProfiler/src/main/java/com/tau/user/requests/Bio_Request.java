package com.tau.user.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Bio_Request {
    private Long user_id;
    private String text_section;

    public Bio_Request(){
        this.user_id = null;
        this.text_section = null;
    }

}
