package com.tenagrim.telegram.service;

import com.tenagrim.telegram.exception.NotFoundException;
import com.tenagrim.telegram.mappers.DataVersionMapper;
import com.tenagrim.telegram.mappers.chapter.ChapterMapper;
import com.tenagrim.telegram.model.Chapter;
import com.tenagrim.telegram.model.DataVersion;
import com.tenagrim.telegram.repository.ChapterRepository;
import com.tenagrim.telegram.repository.DataVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.NotActiveException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VersionService {

    private final DataVersionRepository dataVersionRepository;
    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;
    private final DataVersionMapper dataVersionMapper;

    public List<DataVersion> getVersionsForConfig(Long configId){
        return dataVersionRepository.findAllByBotConfigVersionId(configId);
    }

    public DataVersion getVersionById(Long id){
        return dataVersionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public DataVersion createNewVersion(DataVersion baseVersion, String note){
        DataVersion newVersion = dataVersionMapper.map(baseVersion);
        newVersion.setNote(note);
        DataVersion savedVersion = dataVersionRepository.save(newVersion);

        List<Chapter> chapters = chapterMapper.map(chapterRepository.findAllByDataVersionId(baseVersion.getId()), savedVersion);

        List<Chapter> chapters1 = chapterRepository.saveAll(chapters);
        return newVersion;
    }
}
