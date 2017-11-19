package ch.renewinkler.springbatchxml.dto;

import ch.renewinkler.springbatchxml.entity.Grade;
import lombok.Data;

@Data
public class ResultDTO {

    private Long id;

    private Grade grade;

    private double percentage;

}
