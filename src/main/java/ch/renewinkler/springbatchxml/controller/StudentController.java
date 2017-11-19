package ch.renewinkler.springbatchxml.controller;

import ch.renewinkler.springbatchxml.dto.StudentDTO;
import ch.renewinkler.springbatchxml.dto.mapper.StudentMapper;
import ch.renewinkler.springbatchxml.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository repo;

    @Autowired
    StudentMapper mapper;

    @GetMapping("/students")
    public List<StudentDTO> findAll() {
        return mapper.convertToDTO(repo.findAll());
    }

    @GetMapping("/students/{id}")
    public StudentDTO findOne(@PathVariable Long id) {
        return mapper.convertToDTO(repo.findOne(id));
    }
}
