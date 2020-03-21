package com.aikam.testtask.stat;

import java.sql.Date;

public class StatInterval {
    private Date startDate;
    private Date endDate;

    public StatInterval(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int totalDays() {
        long millisDifference = endDate.getTime() - startDate.getTime();
        long daysDifference = millisDifference / (24 * 60 * 60 * 1000);

        return (int) daysDifference;
    }
}
