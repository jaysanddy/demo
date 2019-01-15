package com.vk.model;

import java.util.Date;

public class InspectionReform {
    private Integer id;

    private Integer recordId;

    private String content;

    private String images;

    private Integer reformUserId;

    private String reformUserName;

    private Date reformTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Integer getReformUserId() {
        return reformUserId;
    }

    public void setReformUserId(Integer reformUserId) {
        this.reformUserId = reformUserId;
    }

    public String getReformUserName() {
        return reformUserName;
    }

    public void setReformUserName(String reformUserName) {
        this.reformUserName = reformUserName == null ? null : reformUserName.trim();
    }

    public Date getReformTime() {
        return reformTime;
    }

    public void setReformTime(Date reformTime) {
        this.reformTime = reformTime;
    }
}