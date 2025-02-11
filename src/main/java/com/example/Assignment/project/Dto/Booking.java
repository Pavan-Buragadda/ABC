package com.example.Assignment.project.Dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
public class Booking {
    private Long id;

    @NotBlank(message = "Member name is required")
    private String memberName;

    @NotNull(message = "Class ID is required")
    private Long classId;

    @NotNull(message = "Participation date is required")
    @Future(message = "Participation date must be in the future")
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

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(LocalDate participationDate) {
        this.participationDate = participationDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", memberName='" + memberName + '\'' +
                ", classId=" + classId +
                ", participationDate=" + participationDate +
                '}';
    }
}
