package com.gym.gym.base.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PageModel {
    protected Map<String, String> f;
    protected Integer pageStart;
    protected Integer pageItems;
    @JsonIgnore
    protected Integer totalRows;
    protected List<String> sort;

    private static Map<String, String> $default$f() {
        return new TreeMap();
    }

    private static Integer $default$pageStart() {
        return 1;
    }

    private static Integer $default$pageItems() {
        return 20;
    }

    private static List<String> $default$sort() {
        return new ArrayList();
    }

    public static PageModelBuilder builder() {
        return new PageModelBuilder();
    }

    public Map<String, String> getF() {
        return this.f;
    }

    public Integer getPageStart() {
        return this.pageStart;
    }

    public Integer getPageItems() {
        return this.pageItems;
    }

    public Integer getTotalRows() {
        return this.totalRows;
    }

    public List<String> getSort() {
        return this.sort;
    }

    public void setF(final Map<String, String> f) {
        this.f = f;
    }

    public void setPageStart(final Integer pageStart) {
        this.pageStart = pageStart;
    }

    public void setPageItems(final Integer pageItems) {
        this.pageItems = pageItems;
    }

    @JsonIgnore
    public void setTotalRows(final Integer totalRows) {
        this.totalRows = totalRows;
    }

    public void setSort(final List<String> sort) {
        this.sort = sort;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageModel)) {
            return false;
        } else {
            PageModel other = (PageModel)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$pageStart = this.getPageStart();
                    Object other$pageStart = other.getPageStart();
                    if (this$pageStart == null) {
                        if (other$pageStart == null) {
                            break label71;
                        }
                    } else if (this$pageStart.equals(other$pageStart)) {
                        break label71;
                    }

                    return false;
                }

                Object this$pageItems = this.getPageItems();
                Object other$pageItems = other.getPageItems();
                if (this$pageItems == null) {
                    if (other$pageItems != null) {
                        return false;
                    }
                } else if (!this$pageItems.equals(other$pageItems)) {
                    return false;
                }

                label57: {
                    Object this$totalRows = this.getTotalRows();
                    Object other$totalRows = other.getTotalRows();
                    if (this$totalRows == null) {
                        if (other$totalRows == null) {
                            break label57;
                        }
                    } else if (this$totalRows.equals(other$totalRows)) {
                        break label57;
                    }

                    return false;
                }

                Object this$f = this.getF();
                Object other$f = other.getF();
                if (this$f == null) {
                    if (other$f != null) {
                        return false;
                    }
                } else if (!this$f.equals(other$f)) {
                    return false;
                }

                Object this$sort = this.getSort();
                Object other$sort = other.getSort();
                if (this$sort == null) {
                    if (other$sort == null) {
                        return true;
                    }
                } else if (this$sort.equals(other$sort)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageModel;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $pageStart = this.getPageStart();
        result = result * 59 + ($pageStart == null ? 43 : $pageStart.hashCode());
        Object $pageItems = this.getPageItems();
        result = result * 59 + ($pageItems == null ? 43 : $pageItems.hashCode());
        Object $totalRows = this.getTotalRows();
        result = result * 59 + ($totalRows == null ? 43 : $totalRows.hashCode());
        Object $f = this.getF();
        result = result * 59 + ($f == null ? 43 : $f.hashCode());
        Object $sort = this.getSort();
        result = result * 59 + ($sort == null ? 43 : $sort.hashCode());
        return result;
    }

    public String toString() {
        Map var10000 = this.getF();
        return "PageModel(f=" + var10000 + ", pageStart=" + this.getPageStart() + ", pageItems=" + this.getPageItems() + ", totalRows=" + this.getTotalRows() + ", sort=" + this.getSort() + ")";
    }

    public PageModel() {
        this.f = $default$f();
        this.pageStart = $default$pageStart();
        this.pageItems = $default$pageItems();
        this.sort = $default$sort();
    }

    public PageModel(final Map<String, String> f, final Integer pageStart, final Integer pageItems, final Integer totalRows, final List<String> sort) {
        this.f = f;
        this.pageStart = pageStart;
        this.pageItems = pageItems;
        this.totalRows = totalRows;
        this.sort = sort;
    }

    public static class PageModelBuilder {
        private boolean f$set;
        private Map<String, String> f$value;
        private boolean pageStart$set;
        private Integer pageStart$value;
        private boolean pageItems$set;
        private Integer pageItems$value;
        private Integer totalRows;
        private boolean sort$set;
        private List<String> sort$value;

        PageModelBuilder() {
        }

        public PageModelBuilder f(final Map<String, String> f) {
            this.f$value = f;
            this.f$set = true;
            return this;
        }

        public PageModelBuilder pageStart(final Integer pageStart) {
            this.pageStart$value = pageStart;
            this.pageStart$set = true;
            return this;
        }

        public PageModelBuilder pageItems(final Integer pageItems) {
            this.pageItems$value = pageItems;
            this.pageItems$set = true;
            return this;
        }

        @JsonIgnore
        public PageModelBuilder totalRows(final Integer totalRows) {
            this.totalRows = totalRows;
            return this;
        }

        public PageModelBuilder sort(final List<String> sort) {
            this.sort$value = sort;
            this.sort$set = true;
            return this;
        }

        public PageModel build() {
            Map<String, String> f$value = this.f$value;
            if (!this.f$set) {
                f$value = PageModel.$default$f();
            }

            Integer pageStart$value = this.pageStart$value;
            if (!this.pageStart$set) {
                pageStart$value = PageModel.$default$pageStart();
            }

            Integer pageItems$value = this.pageItems$value;
            if (!this.pageItems$set) {
                pageItems$value = PageModel.$default$pageItems();
            }

            List<String> sort$value = this.sort$value;
            if (!this.sort$set) {
                sort$value = PageModel.$default$sort();
            }

            return new PageModel(f$value, pageStart$value, pageItems$value, this.totalRows, sort$value);
        }

        public String toString() {
            return "PageModel.PageModelBuilder(f$value=" + this.f$value + ", pageStart$value=" + this.pageStart$value + ", pageItems$value=" + this.pageItems$value + ", totalRows=" + this.totalRows + ", sort$value=" + this.sort$value + ")";
        }
    }
}
