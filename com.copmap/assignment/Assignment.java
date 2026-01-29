package com.copmap.assignment;

import com.copmap.common.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class Assignment extends BaseEntity {

    private Long patrolId;
    private Long officerId;

    private String dutyRole; // LEAD, SUPPORT
}
