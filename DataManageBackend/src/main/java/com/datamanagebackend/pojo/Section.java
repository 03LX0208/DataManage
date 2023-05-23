package com.datamanagebackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @TableId(type = IdType.AUTO)
    private Integer sectionId;
    private Integer courseId;
    private Integer teacherId;
    private Integer classroomId;
    private String sectionTime;
}