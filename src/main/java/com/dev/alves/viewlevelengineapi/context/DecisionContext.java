package com.dev.alves.viewlevelengineapi.context;

import com.dev.alves.viewlevelengineapi.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DecisionContext {

    private CustomerDTO customerDTO;
    private List<String> permissions;

}
