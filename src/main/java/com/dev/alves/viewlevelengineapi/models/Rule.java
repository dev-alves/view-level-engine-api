package com.dev.alves.viewlevelengineapi.models;

import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rule")
public class Rule {

    @Id
    private Long id;
    private StatusEnum status;

    @ElementCollection
    private List<Node> nodes;
}
