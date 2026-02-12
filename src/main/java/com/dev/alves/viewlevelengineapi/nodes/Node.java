package com.dev.alves.viewlevelengineapi.nodes;

public sealed interface Node permits ConditionNode, ActionNode {

    String type();

}
