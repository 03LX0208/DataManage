package com.datamanagebackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    private Integer classroomId;
    private String classroomName;
    private String classroomSite;
}
