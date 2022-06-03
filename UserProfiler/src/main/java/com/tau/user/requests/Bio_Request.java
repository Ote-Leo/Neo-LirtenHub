package com.tau.user.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Bio_Request {
    private final Long user_id;
    private final String text_section;

    public Bio_Request(){
        this.user_id = null;
        this.text_section = null;
    }

}
