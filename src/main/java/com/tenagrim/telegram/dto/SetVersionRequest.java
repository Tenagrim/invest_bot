package com.tenagrim.telegram.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SetVersionRequest extends BotConfigRequest{
    Long targetVersionId;
}

