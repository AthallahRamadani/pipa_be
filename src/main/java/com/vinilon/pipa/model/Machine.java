package com.vinilon.pipa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vinilon.pipa.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "machines")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status machineStatus;

    private Integer diameterMin;
    private Integer diameterMax;

    @OneToOne
    @JoinColumn(name = "attached_body_die_id")
    @JsonBackReference
    private BodyDies attachedBodyDies;
}

