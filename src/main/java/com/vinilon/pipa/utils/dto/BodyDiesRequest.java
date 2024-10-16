package com.vinilon.pipa.utils.dto;

import com.vinilon.pipa.model.enums.BodyDiesStatus;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyDiesRequest {
    private String name;
    private BodyDiesStatus bodyDiesStatus;
    private List<String> machines;
}
