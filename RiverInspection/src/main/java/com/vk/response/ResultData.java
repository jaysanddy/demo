package com.vk.response;

/**
 * Created by wj on 18-4-19.
 */
public class ResultData extends Result{

    private Integer page;

    private Long totalCount;

    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public ResultData(Integer code, Integer page, Long totalCount, Integer totalPages, Object data, String msg) {
        super.setCode(code);
        this.page = page;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
        super.setData(data);
        super.setMsg(msg);
    }

    public ResultData() {

    }

    public ResultData(Integer code, Object data, String msg) {
        super.setCode(code);
        super.setData(data);
        super.setMsg(msg);
    }
}
