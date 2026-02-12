package com.dev.alves.viewlevelengineapi.request;

import java.util.List;

public class CreateRuleRequest {

    private int version;
    private String startNode;
    private List<NodeRequest> nodes;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getStartNode() {
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public List<NodeRequest> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeRequest> nodes) {
        this.nodes = nodes;
    }
}
