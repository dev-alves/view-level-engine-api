package com.dev.alves.viewlevelengineapi.context;

import com.dev.alves.viewlevelengineapi.dto.CustomerDTO;
import com.dev.alves.viewlevelengineapi.dto.NodeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class DecisionContext {

    private CustomerDTO customerDTO;
    private List<String> permissions;

}
