package com.example.tuna.hui.model;

import org.jetbrains.annotations.NotNull;

/**
 * 单词书类
 */
public class Target {

    /**
     * 单词书id
     */
    private Long id;

    /**
     * 单词书名称
     */
    private String name;

    /**
     * 单词书备注
     */
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    @NotNull
    @Override
    public String toString() {
        return "Target{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
