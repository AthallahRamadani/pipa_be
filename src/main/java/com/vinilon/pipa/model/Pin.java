package com.vinilon.pipa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vinilon.pipa.model.enums.Status;
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
@Table(name = "pins")
public class Pin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status pinStatus;

    private List<String> bodyDies;
}
