package com.datamanagebackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private Integer teacherId;
    private String teacherName;
    private String teacherGender;
    private Integer teacherAge;
    private String teacherTelephone;
}
