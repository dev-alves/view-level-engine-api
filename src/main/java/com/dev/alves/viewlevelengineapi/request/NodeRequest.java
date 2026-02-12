package com.dev.alves.viewlevelengineapi.request;


import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;

public class NodeRequest {

    private Long id;
    private NodeTypeEnum type;
    private String operation;
    private ArgumentsRequest arguments;
    private String onTrue;
    private String onFalse;
    private String set;
    private String next;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NodeTypeEnum getType() {
        return type;
    }

    public void setType(NodeTypeEnum type) {
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public ArgumentsRequest getArguments() {
        return arguments;
    }

    public void setArguments(ArgumentsRequest arguments) {
        this.arguments = arguments;
    }

    public String getOnTrue() {
        return onTrue;
    }

    public void setOnTrue(String onTrue) {
        this.onTrue = onTrue;
    }

    public String getOnFalse() {
        return onFalse;
    }

    public void setOnFalse(String onFalse) {
        this.onFalse = onFalse;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
