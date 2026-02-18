package com.dev.alves.viewlevelengineapi.requests;

import com.dev.alves.viewlevelengineapi.dto.NodeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreateRuleRequest {

    private int version;
    private String startNode;
    private Map<String, NodeDTO> nodes;

}
