package com.datamanagebackend.service.impl.classroom;

import com.datamanagebackend.mapper.ClassroomMapper;
import com.datamanagebackend.mapper.SectionMapper;
import com.datamanagebackend.pojo.*;
import com.datamanagebackend.service.classroom.ClassroomCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassroomCrudServiceImpl implements ClassroomCrudService {
    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public Map<String, String> addClassroom(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        String _classroom_id = data.get("classroom_id");
        if (_classroom_id == null || _classroom_id.trim().length() == 0) {
            res.put("error_message", "教室编号不能为空！");
            return res;
        }
        for (int i = 0; i < _classroom_id.length(); i++) {
            if (!(_classroom_id.charAt(i) >= '0' && _classroom_id.charAt(i) <= '9')) {
                res.put("error_message", "教室编号只能由数字组成！");
                return res;
            }
        }
        Integer classroom_id = Integer.parseInt(_classroom_id);
        Classroom classroom = classroomMapper.selectClassroomByClassroomId(classroom_id);
        if (classroom != null) {
            res.put("error_message", "教室编号已存在！");
            return res;
        }

        String classroom_name = data.get("classroom_name");
        if (classroom_name == null || classroom_name.trim().length() == 0) {
            res.put("error_message", "教室名称不能为空！");
            return res;
        }
        classroom_name = classroom_name.trim();

        String classroom_site = data.get("classroom_site");
        if (classroom_site == null || classroom_site.trim().length() == 0) {
            res.put("error_message", "教学楼不能为空！");
            return res;
        }
        classroom_site = classroom_site.trim();

        classroomMapper.insertClassroom(classroom_id, classroom_name, classroom_site);
        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> deleteClassroom(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();
        if (data.get("classroom_id") == null) {
            res.put("error_message", "学院不存在！");
            return res;
        }
        Integer classroom_id = Integer.parseInt(data.get("classroom_id"));

        // 开课外键约束
        List<Section> sections = sectionMapper.selectSectionsByClassroomId(classroom_id);

        if (sections.size() != 0) {
            res.put("error_message", "仍有以该教室作为上课地点的课程！");
            return res;
        }

        classroomMapper.deleteClassroomByClassroomId(classroom_id);
        res.put("error_message", "success");
        return res;
    }
}
