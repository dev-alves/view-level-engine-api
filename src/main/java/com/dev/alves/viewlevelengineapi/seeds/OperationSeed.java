package com.dev.alves.viewlevelengineapi.seeds;

import com.dev.alves.viewlevelengineapi.conditions.ConditionOperator;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import com.dev.alves.viewlevelengineapi.enums.ViewLevelEnum;
import com.dev.alves.viewlevelengineapi.models.Operation;
import com.dev.alves.viewlevelengineapi.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OperationSeed implements CommandLineRunner {

    private final OperationRepository operationRepository;
    private final List<ConditionOperator> conditionOperators;

    @Override
    public void run(String... args) {
        if (operationRepository.count() > 0) {
            return;
        }

        var operations = conditionOperators.stream()
            .map(operator -> {
                return new Operation(NodeTypeEnum.CONDITION, operator.operation().name(), operator.hasArgs());
            })
            .toList();

        if (!operations.isEmpty()) {
            operationRepository.saveAll(operations);
            operationRepository.save(new Operation(NodeTypeEnum.ACTION, ViewLevelEnum.COMPLETE.name(), false));
            operationRepository.save(new Operation(NodeTypeEnum.ACTION, ViewLevelEnum.MASKED.name(), false));
        }
    }
}
