package com.example.fds.model;

import java.util.ArrayList;

public class HomeHeader {
    String title;
    ArrayList<HomeHeaderMapping> homeHeaderMappings;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<HomeHeaderMapping> getHomeHeaderMappings() {
        return homeHeaderMappings;
    }

    public void setHomeHeaderMappings(ArrayList<HomeHeaderMapping> homeHeaderMappings) {
        this.homeHeaderMappings = homeHeaderMappings;
    }
}
