package com.datamanagebackend.service.section;

import com.datamanagebackend.service.impl.section.Util.Timeline;

import java.util.List;
import java.util.Map;

public interface QueryService {
    List<Map<String, String>> getCoursesByTeacherId(Integer teacher_id);
    List<Timeline> getTimelineByTeacherId(Integer teacher_id);
}
