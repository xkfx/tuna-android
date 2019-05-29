package com.example.tuna.hui.model;

import org.jetbrains.annotations.NotNull;

/**
 * 单词卡片类
 */
public class Card {

    /**
     * 单词卡片id
     */
    private Long id;

    /**
     * front用于存储单词
     */
    private String front;

    /**
     * 自定义的单词释义信息，在该APP中不使用该字段，
     * 而是调用第三方API获取单词释义。
     */
    private String back;

    /**
     * 单词所属的单词书id
     */
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
