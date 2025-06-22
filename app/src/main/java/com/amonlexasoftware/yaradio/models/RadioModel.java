package com.amonlexasoftware.yaradio.models;

import java.io.Serializable;

public class RadioModel implements Serializable {

    String name;

    int logo;

    String url;

    String channel;

    public RadioModel(String name, int logo, String url, String channel) {
        this.name = name;
        this.logo = logo;
        this.url = url;
        this.channel = channel;
    }

    public String getName() {
        return this.name;
    }

    public int getLogo() {
        return this.logo;
    }

    public String getUrl() {
        return Url;
    }

    public String getDescription() {
        return Channel;
    }
}
