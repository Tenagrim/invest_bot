package com.tenagrim.telegram.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VersionRequest extends BotConfigRequest{
    Long targetVersionId;
}

