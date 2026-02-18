package com.dev.alves.viewlevelengineapi.context;

import com.dev.alves.viewlevelengineapi.dto.CustomerDTO;
import com.dev.alves.viewlevelengineapi.models.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DecisionContext {

    private int version;
    private String startNode;
    private Map<String, Node> nodes;
    private CustomerDTO customerDTO;
    private List<String> permissions;

}
