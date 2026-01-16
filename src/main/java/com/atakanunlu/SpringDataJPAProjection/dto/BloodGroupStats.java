package com.atakanunlu.SpringDataJPAProjection.dto;

import com.atakanunlu.SpringDataJPAProjection.entity.type.BloodGroupType;
import lombok.Data;

@Data
public class BloodGroupStats {
    private final BloodGroupType bloodGroupType;
    private final Long count;
}
