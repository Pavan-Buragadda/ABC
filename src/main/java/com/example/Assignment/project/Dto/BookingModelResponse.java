package com.example.Assignment.project.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingModelResponse {
    private Long id;
    private String memberName;
    private String className;
    private LocalTime classStartTime;
    private LocalDate participationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalTime getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(LocalTime classStartTime) {
        this.classStartTime = classStartTime;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(LocalDate participationDate) {
        this.participationDate = participationDate;
    }

    @Override
    public String toString() {
        return "BookingModelResponse{" +
                "id=" + id +
                ", memberName='" + memberName + '\'' +
                ", className='" + className + '\'' +
                ", classStartTime=" + classStartTime +
                ", participationDate=" + participationDate +
                '}';
    }
}
