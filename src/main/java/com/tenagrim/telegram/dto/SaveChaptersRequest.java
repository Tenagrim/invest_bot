package com.tenagrim.telegram.dto;

import com.tenagrim.telegram.model.Chapter;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class SaveChaptersRequest {

    private List<Chapter> chapters;
}
