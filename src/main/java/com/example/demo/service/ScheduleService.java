package com.example.demo.service;

import com.example.demo.model.TimeTable;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final Solver<TimeTable> solver;
    private TimeTable bestSolution;

    public ScheduleService(SolverFactory<TimeTable> solverFactory) {
        this.solver = solverFactory.buildSolver();
    }

    public TimeTable solve(TimeTable problem) {
        this.bestSolution = solver.solve(problem);
        return bestSolution;
    }

    public TimeTable getCurrentSolution() {
        return bestSolution;
    }
}
