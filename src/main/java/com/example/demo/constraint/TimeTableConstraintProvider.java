package com.example.demo.constraint;

import com.example.demo.model.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class TimeTableConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[]{
                roomConflict(factory),
                teacherConflict(factory),
                studentGroupConflict(factory)
        };
    }

    private Constraint roomConflict(ConstraintFactory factory) {
        return factory.forEach(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getRoom),
                        Joiners.lessThan(Lesson::hashCode))
                .penalize(HardSoftScore.ONE_HARD, (l1, l2) -> 1)
                .asConstraint("Room conflict");
    }

    private Constraint teacherConflict(ConstraintFactory factory) {
        return factory.forEach(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getTeacher),
                        Joiners.lessThan(Lesson::hashCode))
                .penalize(HardSoftScore.ONE_HARD, (l1, l2) -> 1)
                .asConstraint("Teacher conflict");
    }

    private Constraint studentGroupConflict(ConstraintFactory factory) {
        return factory.forEach(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getStudentGroup),
                        Joiners.lessThan(Lesson::hashCode))
                .penalize(HardSoftScore.ONE_HARD, (l1, l2) -> 1)
                .asConstraint("Group conflict");
    }

    // TODO: soft штрафы за окна и т.п.
}
