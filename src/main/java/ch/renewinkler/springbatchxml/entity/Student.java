package ch.renewinkler.springbatchxml.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "results"})
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Result> results = new ArrayList<>();

    public List<Result> getResults() {
        return Collections.unmodifiableList(results);
    }

    public void setResults(List<Result> results) {
        results.forEach( result -> this.addResult(result));
    }

    public void addResult(Result result) {
        results.add(result);
        result.setStudent(this);
    }

    public void removeResult(Result result) {
        results.remove(result);
        result.setStudent(null);
    }

}
