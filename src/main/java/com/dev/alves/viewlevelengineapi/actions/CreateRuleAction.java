package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.enums.ViewLevelEnum;
import com.dev.alves.viewlevelengineapi.requests.CreateRuleRequest;
import com.dev.alves.viewlevelengineapi.responses.ViewLevelResponse;
import com.dev.alves.viewlevelengineapi.services.RuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateRuleAction {

    @Autowired
    private RuleEngineService ruleEngineService;

    public ViewLevelResponse execute(CreateRuleRequest createRuleRequest) {
        var viewLevelDefault = new ViewLevelResponse();
        viewLevelDefault.setViewLevel(ViewLevelEnum.COMPLETE);
        viewLevelDefault.setViewLevel(ruleEngineService.execute(createRuleRequest));
        return viewLevelDefault;
    }

}
