package com.tenagrim.telegram.controller;

import com.tenagrim.telegram.controller.base.SecuredRestController;
import com.tenagrim.telegram.dto.BotConfigRequest;
import com.tenagrim.telegram.dto.VersionRequest;
import com.tenagrim.telegram.model.BotConfig;
import com.tenagrim.telegram.model.DataVersion;
import com.tenagrim.telegram.service.BotConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
//@Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
//@SecurityRequirement(name = "bearerAuth")
public class ConfigController implements SecuredRestController {
    private final BotConfigService botConfigService;

    @PostMapping("/getBySysName")
    BotConfig getConfigBySysName(@RequestBody @Valid BotConfigRequest request){
        return botConfigService.getConfigBySysName(request.getSysName());
    }

    @PostMapping("/getVersionsBySysName")
    List<DataVersion> getVersionsBySysName(@RequestBody @Valid BotConfigRequest request){
        return botConfigService.getVersionsBySysName(request.getSysName());
    }

    @PostMapping("/createVersionFromCurrent")
    BotConfig createVersionFromCurrent(@RequestBody @Valid BotConfigRequest request){
        return botConfigService.createNewVersionFromCurrent(request.getSysName());
    }

    @PostMapping("/createVersionFromTarget")
    DataVersion createVersionFromCurrent(@RequestBody @Valid VersionRequest request){
        return botConfigService.createNewVersionFromTarget(request.getSysName(), request.getTargetVersionId());
    }

    @PostMapping("/setCurrentVersion")
    BotConfig setCurrentVersion(@RequestBody @Valid VersionRequest request){
        return botConfigService.setCurrentVersion(request.getSysName(), request.getTargetVersionId());
    }

}
