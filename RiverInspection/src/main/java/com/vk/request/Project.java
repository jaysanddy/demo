package com.vk.request;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by weijin on 2018/6/29.
 */
@Component
@ConfigurationProperties(prefix="project")
public class Project {
    private String uploadFilePath;

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }
}
