package com.tenagrim.telegram.dto;

import com.tenagrim.telegram.model.chapter.Chapter;
import lombok.Data;

import java.util.List;

@Data
public class SaveChaptersRequest {

    private List<Chapter> chapters;
}
