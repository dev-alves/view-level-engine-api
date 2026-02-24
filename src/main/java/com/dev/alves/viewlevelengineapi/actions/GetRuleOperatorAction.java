package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.dto.RuleOperatorDTO;
import com.dev.alves.viewlevelengineapi.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetRuleOperatorAction {

    private OperationRepository operationRepository;

    public List<RuleOperatorDTO> execute() {
        return RuleOperatorDTO.toDTOS(operationRepository.findAll());
    }

}
