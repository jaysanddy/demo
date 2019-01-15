package com.vk.model;

import java.util.List;

public class RiverQuestion {
    private Integer id;

    private String name;

    private String reamark;

    private Integer level;

    private Integer parent;

    private List<InspectionQuestion> secondQuestions;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<InspectionQuestion> getSecondQuestions() {
        return secondQuestions;
    }

    public void setSecondQuestions(List<InspectionQuestion> secondQuestions) {
        this.secondQuestions = secondQuestions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getReamark() {
        return reamark;
    }

    public void setReamark(String reamark) {
        this.reamark = reamark == null ? null : reamark.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }
}