package com.tenagrim.telegram.service;

import com.tenagrim.telegram.exception.NotFoundException;
import com.tenagrim.telegram.model.BotConfig;
import com.tenagrim.telegram.model.DataVersion;
import com.tenagrim.telegram.repository.BotConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BotConfigService {
    private final BotConfigRepository botConfigRepository;
    private final VersionService versionService;
    private final BotResolver botResolver;
    public BotConfig getConfigBySysName(String sysName){
        return botConfigRepository.findBySysName(sysName)
                .orElseThrow(NotFoundException::new);
    }

    public List<DataVersion> getVersionsBySysName(String sysName) {
        BotConfig config = getConfigBySysName(sysName);

        return versionService.getVersionsForConfig(config.getBotConfigVersion().getId());
    }

    public BotConfig setCurrentVersion(String sysName, Long targetVersionId){
        BotConfig config = getConfigBySysName(sysName);
        DataVersion version = versionService.getVersionById(targetVersionId);
        if (!Objects.equals(config.getCurrentVersion().getId(), version.getId())){
            config.setCurrentVersion(version);
            BotConfig savedConfig = botConfigRepository.save(config);
            botResolver.getBot().setBotConfig(savedConfig);
            return savedConfig;
        }
        return config;
    }

    @Transactional
    public BotConfig createNewVersionFromCurrent(String sysName){
        BotConfig config = getConfigBySysName(sysName);
        long maxVersionId = config.getBotConfigVersion().getDataVersions().stream().mapToLong(DataVersion::getId).max().orElse(0L);
        DataVersion newVersion = versionService.createNewVersion(config.getCurrentVersion(), "Версия " + (maxVersionId + 1L));
//        config.setCurrentVersion(newVersion);
        BotConfig savedConfig = botConfigRepository.save(config);
        savedConfig.getBotConfigVersion().getDataVersions().add(newVersion);
        botResolver.getBot().setBotConfig(savedConfig);
        return savedConfig;
    }
}
