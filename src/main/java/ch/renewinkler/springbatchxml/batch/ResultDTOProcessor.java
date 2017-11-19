package ch.renewinkler.springbatchxml.batch;

import ch.renewinkler.springbatchxml.dto.ResultsXmlDTO;
import org.springframework.batch.item.ItemProcessor;

public class ResultDTOProcessor implements ItemProcessor<ResultsXmlDTO, ResultsXmlDTO> {

    @Override
    public ResultsXmlDTO process(ResultsXmlDTO item)  {
        return item;
    }
}
