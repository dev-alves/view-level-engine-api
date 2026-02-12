package com.dev.alves.viewlevelengineapi.nodes;

import java.util.Map;

public record ConditionNode(
        String type,
        String operation,
        Map<String, Object> args,
        String ifTrue,
        String ifFalse
) implements Node { }