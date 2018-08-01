/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * SidebarDto
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 下午6:00:36 
 */

public class IcloudSidebarDto {

    private List<SideBar> navList = new ArrayList<>();

    public static class Item {

        private Long id;

        private String name;

        private String link;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    }

    public static class SideBar {

        private long id;

        private String title;

        private List<Item> childrens = new ArrayList<>();

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Item> getChildrens() {
            return childrens;
        }

        public void setChildrens(List<Item> childrens) {
            this.childrens = childrens;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

    }

    public List<SideBar> getNavList() {
        return navList;
    }

    public void setNavList(List<SideBar> navList) {
        this.navList = navList;
    }

}
