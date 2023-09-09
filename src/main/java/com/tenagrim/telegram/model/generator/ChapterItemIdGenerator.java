package com.tenagrim.telegram.model.generator;

public class ChapterItemIdGenerator extends BaseItemIdGenerator{
    @Override
    protected String getSequenceName() {
        return "chapter_item_id_seq";
    }
}
