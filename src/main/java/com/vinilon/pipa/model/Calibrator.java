package com.vinilon.pipa.model;

import com.vinilon.pipa.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calibrators")
public class Calibrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status calibratorStatus;

    private Integer thicknessMin;
    private Integer thicknessMax;
}
