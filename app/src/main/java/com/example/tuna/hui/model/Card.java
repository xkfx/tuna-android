package com.example.tuna.hui.model;

import org.jetbrains.annotations.NotNull;

public class Card {

    private Long id;

    private String front;

    private String back;

    private Long targetId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front == null ? null : front.trim();
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back == null ? null : back.trim();
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    @NotNull
    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", front='" + front + '\'' +
                ", back='" + back + '\'' +
                ", targetId=" + targetId +
                '}';
    }
}
