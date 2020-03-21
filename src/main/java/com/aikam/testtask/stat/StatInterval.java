package com.aikam.testtask.stat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StatInterval {
    private LocalDate startDate;
    private LocalDate endDate;

    public StatInterval(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int totalDays() {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
