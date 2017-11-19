package ch.renewinkler.springbatchxml.batch;

import ch.renewinkler.springbatchxml.dto.ResultsXmlDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@EnableBatchProcessing
@Configuration
public class XmlFileToDatabaseJobConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job xmlImportJob(){
        return jobs.get("xmlImportJob")
            .start(step())
            .build();
    }

    @Bean
    public Step step(){
        return stepBuilderFactory.get("step")
            .<ResultsXmlDTO,ResultsXmlDTO>chunk(1) //important to be one in this case to commit after every line read
            .reader(reader())
            .processor(processor())
            .writer(writer())
//            .listener(logProcessListener())
//            .faultTolerant()
//            .skipLimit(10) //default is set to 0
//            .skip(MySQLIntegrityConstraintViolationException.class)
            .build();
    }

    @Bean
    ItemReader<ResultsXmlDTO> reader() {
        StaxEventItemReader<ResultsXmlDTO> xmlFileReader = new StaxEventItemReader<>();
        xmlFileReader.setResource(new ClassPathResource("exam_results.xml"));
        xmlFileReader.setFragmentRootElementName("result");

        Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
        studentMarshaller.setClassesToBeBound(ResultsXmlDTO.class);
        xmlFileReader.setUnmarshaller(studentMarshaller);

        return xmlFileReader;
    }

    @Bean
    public ItemProcessor<ResultsXmlDTO, ResultsXmlDTO> processor() {
        return new ResultDTOProcessor();
    }

    @Bean
    public ItemWriter<ResultsXmlDTO> writer() {
        return new ResultDTOWriter();
    }
}
