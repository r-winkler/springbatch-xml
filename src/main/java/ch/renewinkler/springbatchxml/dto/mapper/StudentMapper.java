package ch.renewinkler.springbatchxml.dto.mapper;

import ch.renewinkler.springbatchxml.dto.StudentDTO;
import ch.renewinkler.springbatchxml.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static ch.renewinkler.springbatchxml.TimeUtils.LOCALDATE_FORMATTER;

@Component
public class StudentMapper {

    @Autowired
    ResultMapper resultMapper;

    public StudentDTO convertToDTO(Student in) {
        StudentDTO dto = new StudentDTO();
        dto.setId(in.getId());
        dto.setFirstname(in.getFirstname());
        dto.setLastname(in.getLastname());
        dto.setBirthdate(in.getBirthdate().format(LOCALDATE_FORMATTER));
        dto.setResults(resultMapper.convertToDTO(in.getResults()));
        return dto;
    }

    public List<StudentDTO> convertToDTO(List<Student> students) {
        List<StudentDTO> studentDTOs = new ArrayList<>();
        students.forEach(student -> studentDTOs.add(convertToDTO(student)));
        return studentDTOs;
    }
}
