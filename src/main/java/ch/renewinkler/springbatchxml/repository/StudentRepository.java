package ch.renewinkler.springbatchxml.repository;

import ch.renewinkler.springbatchxml.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
