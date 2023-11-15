package com.tenagrim.telegram.model.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "INTEGRATION_QUEUE")
public class IntegrationQueueRecord {

    public IntegrationQueueRecord(Long id, Long userId, Long integrationTypeId, Boolean actual, Long chapterId) {
        this.id = id;
        this.userId = userId;
        this.integrationTypeId = integrationTypeId;
        this.actual = actual;
        this.chapterId = chapterId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    Long userId;

    @Column(name = "INTEGRATION_TYPE_ID")
    Long integrationTypeId;

    @Column(name = "ACTUAL")
    @ColumnDefault("true")
    Boolean actual;

    @Column(name = "CHAPTER_ID", nullable = false)
    Long chapterId;
}
