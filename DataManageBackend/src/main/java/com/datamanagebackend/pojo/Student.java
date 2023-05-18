package com.datamanagebackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer studentId;
    private String studentName;
    private String studentGender;
    private String studentEnterDate;
    private Integer studentAge;
    private String studentTelephone;
    private Integer facultyId;      //  属于哪个学院..
}
