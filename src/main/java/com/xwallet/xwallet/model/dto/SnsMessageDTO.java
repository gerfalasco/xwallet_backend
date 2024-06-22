package com.xwallet.xwallet.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SnsMessageDTO {
    String operationType;
    Map<String, Object> data;
}
