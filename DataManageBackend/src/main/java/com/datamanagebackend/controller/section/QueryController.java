package com.datamanagebackend.controller.section;

import com.datamanagebackend.pojo.Section;
import com.datamanagebackend.service.impl.section.Util.Timeline;
import com.datamanagebackend.service.section.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/section/query/")
public class QueryController {
    @Autowired
    private QueryService queryService;

    @GetMapping("/by-teacher-id/")
    public List<Map<String, String>> getCoursesByTeacherId(@RequestParam Map<String, String> data) {
        return queryService.getCoursesByTeacherId(Integer.parseInt(data.get("teacher_id")));
    }

    @GetMapping("/timeline/")
    public List<Timeline> getTimelineByTeacherId(@RequestParam Map<String, String> data) {
        return queryService.getTimelineByTeacherId(Integer.parseInt(data.get("teacher_id")));
    }

    @GetMapping("/score/")
    public List<Map<String, Object>> getSectionScoreBySectionId(@RequestParam Map<String, String> data) {
        return queryService.getSectionScore(Integer.parseInt(data.get("section_id")));
    }
}
