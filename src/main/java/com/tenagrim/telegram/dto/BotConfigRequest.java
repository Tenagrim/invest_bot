package com.tenagrim.telegram.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BotConfigRequest {
    @NotEmpty
    @Schema(required = true, example = "INVEST_BOT")
    String sysName;
}
