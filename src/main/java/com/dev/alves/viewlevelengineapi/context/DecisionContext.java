package com.dev.alves.viewlevelengineapi.context;

import com.dev.alves.viewlevelengineapi.dto.CustomerDTO;

import java.util.List;

public class DecisionContext {
    CustomerDTO customerDTO;
    List<String> permissions;

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
