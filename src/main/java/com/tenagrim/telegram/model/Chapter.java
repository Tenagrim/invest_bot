package com.tenagrim.telegram.model;

import com.tenagrim.telegram.model.generator.ChapterItemIdGenerator;
import com.tenagrim.telegram.model.interfaces.IdItemIdHolder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "CHAPTER")
@Getter
@Setter // todo: maybe make immutable
public class Chapter implements IdItemIdHolder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "NOTE")
    String note;

    @Column(name = "TEXT")
    String text;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CHAPTER_ID", referencedColumnName = "ID")
    Set<ChapterButton> chapterButtons;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CHAPTER_ID", referencedColumnName = "ID")
    Set<ChapterAttachement> chapterAttachements;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "NODE_POSITION_ID", referencedColumnName = "ID")
    NodePosition nodePosition;

    @Column(name = "CHAPTER_TYPE_ID")
    Long chapterTypeId;

    @Column(name ="DATA_VERSION_ID")
    Long dataVersionId;

    @Column(name = "MARKS_KEY")
    Long marksKey;

//    @Generated(GenerationTime.INSERT)
    @GeneratorType(type = ChapterItemIdGenerator.class,
        when = GenerationTime.INSERT)
    @Column(name ="ITEM_ID")
    Long itemId;

    @Column(name = "UPDATE_DATE")
    ZonedDateTime updateDate;

    @Column(name = "UPDATE_USER_ID")
    Long updateUserId;
}
