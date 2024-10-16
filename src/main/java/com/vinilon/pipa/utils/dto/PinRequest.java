package com.vinilon.pipa.utils.dto;

import com.vinilon.pipa.model.enums.Status;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PinRequest {
    private String name;
    private Status pinStatus;
    private List<String> bodyDies;

}
