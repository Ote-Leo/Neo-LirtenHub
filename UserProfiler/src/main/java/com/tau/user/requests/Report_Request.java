package com.tau.user.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Report_Request {
    private final Long user_id, reported_id;
    private final String report_description;

    public Report_Request(){
        this.user_id = null;
        this.reported_id = null;
        this.report_description = null;
    }

}
