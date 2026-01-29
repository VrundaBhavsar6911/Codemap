package com.copmap.patrol;

import com.copmap.common.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Patrol extends BaseEntity {

    private String area;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long plannerId;

    private String status; // PLANNED, ACTIVE, COMPLETED
}
