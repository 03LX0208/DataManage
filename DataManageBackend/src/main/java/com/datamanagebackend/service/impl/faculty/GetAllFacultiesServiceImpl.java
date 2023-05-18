package com.datamanagebackend.service.impl.faculty;

import com.datamanagebackend.mapper.FacultyMapper;
import com.datamanagebackend.pojo.Faculty;
import com.datamanagebackend.service.faculty.GetAllFacultiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllFacultiesServiceImpl implements GetAllFacultiesService {
    @Autowired
    private FacultyMapper facultyMapper;

    @Override
    public List<Faculty> gerAll() {
        return facultyMapper.getAll();
    }
}
