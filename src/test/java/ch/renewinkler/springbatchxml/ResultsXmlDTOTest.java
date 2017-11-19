package ch.renewinkler.springbatchxml;

import ch.renewinkler.springbatchxml.dto.ResultsXmlDTO;
import ch.renewinkler.springbatchxml.entity.Grade;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class ResultsXmlDTOTest {

    @Test
    public void testGetFirstname() {
        ResultsXmlDTO dto = ResultsXmlDTO.builder().student("René Winkler").build();
        assertThat(dto.getFirstname()).isEqualTo("René");
    }

    @Test
    public void testGetLastname() {
        ResultsXmlDTO dto = ResultsXmlDTO.builder().student("René Winkler").build();
        assertThat(dto.getLastname()).isEqualTo("Winkler");
    }

    @Test
    public void testGetBirthdate() {
        ResultsXmlDTO dto = ResultsXmlDTO.builder().birthdate("1984-04-09").build();
        assertThat(dto.getBirthdate()).isEqualTo(LocalDate.of(1984,4,9));
    }

    @Test
    public void testGetGrade() {
        ResultsXmlDTO dto = ResultsXmlDTO.builder().grade(" a ").build();
        assertThat(dto.getGrade()).isEqualTo(Grade.A);
    }

    @Test
    public void testGetPercentage() {
        ResultsXmlDTO dto = ResultsXmlDTO.builder().percentage("75.3").build();
        assertThat(dto.getPercentage()).isEqualTo(75.3);
    }

}