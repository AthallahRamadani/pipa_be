package com.vinilon.pipa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinilon.pipa.model.enums.BodyDiesStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "body_dies")
public class BodyDies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BodyDiesStatus bodyDiesStatus;

    private List<String> machines;

    @OneToOne(mappedBy = "attachedBodyDies")
    private Machine attachedMachine;
}
