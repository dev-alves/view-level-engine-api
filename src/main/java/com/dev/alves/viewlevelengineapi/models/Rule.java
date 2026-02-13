package com.dev.alves.viewlevelengineapi.models;

import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rule")
public class Rule {

    @Id
    private Long id;
    private StatusEnum status;

}
