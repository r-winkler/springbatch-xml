package ch.renewinkler.springbatchxml.dto;

import ch.renewinkler.springbatchxml.entity.Grade;
import ch.renewinkler.springbatchxml.entity.Result;
import ch.renewinkler.springbatchxml.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

import static ch.renewinkler.springbatchxml.TimeUtils.LOCALDATE_FORMATTER;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "result")
public class ResultsXmlDTO {

    @XmlElement(name = "student")
    private String student;

    @XmlElement(name = "birthdate")
    private String birthdate;

    @XmlElement(name = "grade")
    private String grade;

    @XmlElement(name = "percentage")
    private String percentage;

    public Student createStudent() {
        return Student.builder()
            .firstname(getFirstname())
            .lastname(getLastname())
            .birthdate(getBirthdate())
            .build();
    }

    public Result createResult() {
        return Result.builder()
            .grade(getGrade())
            .percentage(getPercentage())
            .build();
    }

    public String getFirstname() {
        return student.split("\\s+")[0];
    }

    public String getLastname() {
        return student.split("\\s+")[1];
    }

    public LocalDate getBirthdate() {
        return LocalDate.parse(birthdate, LOCALDATE_FORMATTER);
    }

    public Grade getGrade() {
        return Grade.valueOf(grade.toUpperCase().trim());
    }

    public double getPercentage() {
        return Double.valueOf(percentage);
    }
}
