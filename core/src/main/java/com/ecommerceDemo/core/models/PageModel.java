package com.ecommerceDemo.core.models;

public class PageModel {

    private String parentPage;
    private String pageName;
    private String whichTemplate;
    private String pageTitle;

    public PageModel() {
        super();
    }

    public PageModel(String parentPage, String pageName, String whichTemplate, String pageTitle) {
        this.parentPage = parentPage;
        this.pageName = pageName;
        this.whichTemplate = whichTemplate;
        this.pageTitle = pageTitle;
    }

    public String getParentPage() {
        return parentPage;
    }

    public void setParentPage(String parentPage) {
        this.parentPage = parentPage;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getWhichTemplate() {
        return whichTemplate;
    }

    public void setWhichTemplate(String whichTemplate) {
        this.whichTemplate = whichTemplate;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

}
