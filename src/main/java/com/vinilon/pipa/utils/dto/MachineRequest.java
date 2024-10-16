package com.vinilon.pipa.utils.dto;

import com.vinilon.pipa.model.enums.Status;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MachineRequest {
    private String name;
    private Status machineStatus;
    private Integer diameterMin;
    private Integer diameterMax;
    private Integer attachedBodyDies;
}
