package com.vinilon.pipa.utils.dto;

import com.vinilon.pipa.model.enums.Status;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalibratorResponse {
    private Integer id;
    private String name;
    private Status calibratorStatus;
    private Integer thicknessMin;
    private Integer thicknessMax;
}
