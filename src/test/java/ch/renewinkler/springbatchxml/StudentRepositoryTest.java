package ch.renewinkler.springbatchxml;

import ch.renewinkler.springbatchxml.entity.Grade;
import ch.renewinkler.springbatchxml.entity.Result;
import ch.renewinkler.springbatchxml.entity.Student;
import ch.renewinkler.springbatchxml.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository repository;

    @Test
    public void persistStudent() {
        List<Result> results = new ArrayList<>();
        results.add(Result.builder().grade(Grade.A).percentage(95.0).build());
        results.add(Result.builder().grade(Grade.B).percentage(85.0).build());
        results.add(Result.builder().grade(Grade.C).percentage(75.0).build());

        Student student = Student.builder().firstname("first").lastname("last").birthdate(LocalDate.now()).build();
        student.setResults(results);

        Long id = this.entityManager.persist(student).getId();
        student = this.repository.findOne(id);

        assertThat(student.getFirstname()).isEqualTo("first");
        assertThat(student.getLastname()).isEqualTo("last");
        assertThat(student.getResults()).hasSize(3);
    }

}
