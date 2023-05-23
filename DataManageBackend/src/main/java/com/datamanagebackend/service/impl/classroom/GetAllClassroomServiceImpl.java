package com.datamanagebackend.service.impl.classroom;

import com.datamanagebackend.mapper.ClassroomMapper;
import com.datamanagebackend.pojo.Classroom;
import com.datamanagebackend.service.classroom.GetAllClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllClassroomServiceImpl implements GetAllClassroomService {
    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public List<Classroom> getAllClassroom() {
        return classroomMapper.getAll();
    }
}
