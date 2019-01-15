package com.vk.request;

import java.util.List;
import java.util.Map;

/**
 * Created by weijin on 2018/6/25.
 */
public class InspectionAnswerQuestions {
    private Integer id;
    //private List<Map<String,Object>> detail;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
