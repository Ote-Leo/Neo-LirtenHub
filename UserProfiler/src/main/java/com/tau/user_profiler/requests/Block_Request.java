package com.tau.user_profiler.requests;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Block_Request {
    private final Long user_id, blocked_id;

    public Block_Request(){
        this.user_id = null;
        this.blocked_id = null;
    }

}
