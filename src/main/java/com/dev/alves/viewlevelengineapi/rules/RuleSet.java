package com.dev.alves.viewlevelengineapi.rules;

import com.dev.alves.viewlevelengineapi.nodes.Node;

import java.util.Map;

public record RuleSet(
        String startNode,
        Map<String, Node> nodes
) { }
