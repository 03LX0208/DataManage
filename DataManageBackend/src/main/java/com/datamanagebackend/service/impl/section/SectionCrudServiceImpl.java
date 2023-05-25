package com.datamanagebackend.service.impl.section;

import com.datamanagebackend.mapper.SectionMapper;
import com.datamanagebackend.mapper.StudentSectionMapper;
import com.datamanagebackend.pojo.StudentSection;
import com.datamanagebackend.service.section.SectionCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SectionCrudServiceImpl implements SectionCrudService {
    @Autowired
    private StudentSectionMapper studentSectionMapper;

    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public Map<String, String> addSection(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        String _course_id = data.get("course_id");
        if (_course_id == null || _course_id.trim().length() == 0) {
            res.put("error_message", "课程不能为空！");
            return res;
        }
        Integer course_id = Integer.parseInt(_course_id);

        String _teacher_id = data.get("teacher_id");
        if (_teacher_id == null || _teacher_id.trim().length() == 0) {
            res.put("error_message", "任教老师不能为空！");
            return res;
        }
        Integer teacher_id = Integer.parseInt(_teacher_id);

        String _classroom_id = data.get("classroom_id");
        if (_classroom_id == null || _classroom_id.trim().length() == 0) {
            res.put("error_message", "教室不能为空！");
            return res;
        }
        Integer classroom_id = Integer.parseInt(_classroom_id);

        String section_time = data.get("section_time");
        if (section_time == null || section_time.trim().length() == 0) {
            res.put("error_message", "上课时间不能为空！");
            return res;
        }

        String _capacity = data.get("capacity");
        if (_capacity == null || _capacity.trim().length() == 0) {
            res.put("error_message", "课程容量不能为空！");
            return res;
        }
        Integer capacity = Integer.parseInt(_capacity);

        String[] days = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        String[] hours = {"8:00-8:50", "9:00-9:50", "10:00-10:50", "11:00-11:50", "14:00-14:50", "15:00-15:50", "16:00-16:50", "17:00-17:50"};
        String[] options = section_time.split(" ");
        if (options.length == 0) {
            res.put("error_message", "上课时间不能为空！");
            return res;
        }



        StringBuilder sectionTime = new StringBuilder();
        for (String option : options) {
            int x = Integer.parseInt(option);
            String day = days[x / 8];
            String hour = hours[x % 8];
            sectionTime.append(day).append("/").append(hour).append("/");
        }

        sectionMapper.insertSection(null, course_id, teacher_id, classroom_id, sectionTime.toString(), capacity);

        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> deleteSection(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        Integer section_id = Integer.parseInt(data.get("section_id"));

        List<StudentSection> studentSectionList = studentSectionMapper.selectListBySectionId(section_id);
        if (studentSectionList.size() != 0) {
            res.put("error_message", "仍有选该课的学生！");
            return res;
        }

        sectionMapper.deleteSectionBySectionId(section_id);
        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> updateSection(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        Integer section_id = Integer.parseInt(data.get("section_id"));

        String _course_id = data.get("course_id");
        if (_course_id == null || _course_id.trim().length() == 0) {
            res.put("error_message", "课程不能为空！");
            return res;
        }
        Integer course_id = Integer.parseInt(_course_id);

        String _teacher_id = data.get("teacher_id");
        if (_teacher_id == null || _teacher_id.trim().length() == 0) {
            res.put("error_message", "任教老师不能为空！");
            return res;
        }
        Integer teacher_id = Integer.parseInt(_teacher_id);

        String _classroom_id = data.get("classroom_id");
        if (_classroom_id == null || _classroom_id.trim().length() == 0) {
            res.put("error_message", "教室不能为空！");
            return res;
        }
        Integer classroom_id = Integer.parseInt(_classroom_id);

        String section_time = data.get("section_time");
        if (section_time == null || section_time.trim().length() == 0) {
            res.put("error_message", "上课时间不能为空！");
            return res;
        }

        sectionMapper.updateSectionBySectionId(section_id, course_id, teacher_id, classroom_id, section_time);

        res.put("error_message", "success");
        return res;
    }
}
