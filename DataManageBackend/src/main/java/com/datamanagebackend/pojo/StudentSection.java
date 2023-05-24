package com.datamanagebackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSection {
    private Integer studentId;
    private Integer sectionId;
    private Integer grade;
}
