package com.gym.gym.utils;

public class FetchOptions {
    protected PageModel pageModel;
    protected String fetchProfile;

    FetchOptions(final PageModel pageModel, final String fetchProfile) {
        this.pageModel = pageModel;
        this.fetchProfile = fetchProfile;
    }

    public static FetchOptionsBuilder builder() {
        return new FetchOptionsBuilder();
    }

    public PageModel getPageModel() {
        return this.pageModel;
    }

    public String getFetchProfile() {
        return this.fetchProfile;
    }

    public void setPageModel(final PageModel pageModel) {
        this.pageModel = pageModel;
    }

    public void setFetchProfile(final String fetchProfile) {
        this.fetchProfile = fetchProfile;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof FetchOptions)) {
            return false;
        } else {
            FetchOptions other = (FetchOptions) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$pageModel = this.getPageModel();
                Object other$pageModel = other.getPageModel();
                if (this$pageModel == null) {
                    if (other$pageModel != null) {
                        return false;
                    }
                } else if (!this$pageModel.equals(other$pageModel)) {
                    return false;
                }

                Object this$fetchProfile = this.getFetchProfile();
                Object other$fetchProfile = other.getFetchProfile();
                if (this$fetchProfile == null) {
                    if (other$fetchProfile != null) {
                        return false;
                    }
                } else if (!this$fetchProfile.equals(other$fetchProfile)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FetchOptions;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $pageModel = this.getPageModel();
        result = result * 59 + ($pageModel == null ? 43 : $pageModel.hashCode());
        Object $fetchProfile = this.getFetchProfile();
        result = result * 59 + ($fetchProfile == null ? 43 : $fetchProfile.hashCode());
        return result;
    }

    public String toString() {
        PageModel var10000 = this.getPageModel();
        return "FetchOptions(pageModel=" + var10000 + ", fetchProfile=" + this.getFetchProfile() +")";
    }

    public static class FetchOptionsBuilder {
        private PageModel pageModel;
        private String fetchProfile;
        private boolean filterTerritoriesActive$set;
        private boolean filterTerritoriesActive$value;

        FetchOptionsBuilder() {
        }

        public FetchOptionsBuilder pageModel(final PageModel pageModel) {
            this.pageModel = pageModel;
            return this;
        }

        public FetchOptionsBuilder fetchProfile(final String fetchProfile) {
            this.fetchProfile = fetchProfile;
            return this;
        }

        public String toString() {
            return "FetchOptions.FetchOptionsBuilder(pageModel=" + this.pageModel + ", fetchProfile=" + this.fetchProfile + ")";
        }
    }
}
