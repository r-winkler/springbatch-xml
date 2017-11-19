package ch.renewinkler.springbatchxml.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String birthdate;

    private List<ResultDTO> results;
}
