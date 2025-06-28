package com.example.demo.model;

import lombok.Data;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
@Data
public class Lesson {
    private String subject;
    private String teacher;
    private StudentGroup studentGroup;

    @PlanningVariable(valueRangeProviderRefs = {"timeslotRange"})
    private Timeslot timeslot;

    @PlanningVariable(valueRangeProviderRefs = {"roomRange"})
    private Room room;
}
