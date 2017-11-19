package ch.renewinkler.springbatchxml.batch;


import ch.renewinkler.springbatchxml.dto.ResultsXmlDTO;
import ch.renewinkler.springbatchxml.entity.Result;
import ch.renewinkler.springbatchxml.entity.Student;
import ch.renewinkler.springbatchxml.repository.StudentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ResultDTOWriter implements ItemWriter<ResultsXmlDTO> {

    @Autowired
    private StudentRepository repo;


    @Override
    public void write(List<? extends ResultsXmlDTO> items) throws Exception {

        if(items.get(0) != null){
            ResultsXmlDTO resultDTO = items.get(0);

            Student student = resultDTO.createStudent();
            Result result = resultDTO.createResult();

            // TODO: check if student already exists

            student.addResult(result);
            repo.save(student);
        }

    }

}
