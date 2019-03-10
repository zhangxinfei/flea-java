package com.soecode.lyf.util.pageUtil;

/**
 *
 * @auther zhangxinfei
 * @data 2018/12/2 14:29
 */
public class PageUtil implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3954548622427149671L;
    private Integer limit = 10;// 信息列表分页大小
    private Integer page = 1;// 起始条数位置
    private Integer count = 0; // 总条数

    private String order; // asc/desc
    private String sort;// 排序字段

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    // 获取总页数
    public Integer getTotalPage() {
        Integer totalPage = count / this.limit + (count % this.limit == 0 ? 0 : 1);
        return totalPage;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}

