package com.example.assignment.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private Integer id;
    private String message;
    @DateTimeFormat
    private Date timeStamp;
    private Boolean isSent;

}
