package io.fusionauth.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

  private boolean success;

  private String message;

  private Map<String, Object> data;
}
