package com.villamorvinzie.view.domain;

import com.villamorvinzie.view.enums.ActivityType;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "activities")
public class Activity {

    @Id private String id;
    @NotEmpty private String userId;
    @NotEmpty private boolean isDeleted;
    @NotEmpty private ActivityType type;
    private boolean isDone;
    private LocalDate dueDate;
    private Double amount;

    public Activity(
            String id,
            @NotEmpty String userId,
            @NotEmpty boolean isDeleted,
            @NotEmpty ActivityType type,
            boolean isDone,
            LocalDate dueDate,
            Double amount) {
        this.id = id;
        this.userId = userId;
        this.isDeleted = isDeleted;
        this.type = type;
        this.isDone = isDone;
        this.dueDate = dueDate;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
