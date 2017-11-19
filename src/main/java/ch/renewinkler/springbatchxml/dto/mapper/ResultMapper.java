package ch.renewinkler.springbatchxml.dto.mapper;

import ch.renewinkler.springbatchxml.dto.ResultDTO;
import ch.renewinkler.springbatchxml.entity.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultMapper {

    public ResultDTO convertToDTO(Result in) {
        ResultDTO dto = new ResultDTO();
        dto.setId(in.getId());
        dto.setGrade(in.getGrade());
        dto.setPercentage(in.getPercentage());
        return dto;
    }

    public List<ResultDTO> convertToDTO(List<Result> results) {
        List<ResultDTO> resultDTOs = new ArrayList<>();
        results.forEach(result -> resultDTOs.add(convertToDTO(result)));
        return resultDTOs;
    }

    public Result convertToEntity(ResultDTO in) {
        Result out = new Result();
        out.setId(in.getId());
        out.setGrade(in.getGrade());
        out.setPercentage(in.getPercentage());
        return out;
    }

}
