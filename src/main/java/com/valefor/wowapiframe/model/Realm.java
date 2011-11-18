package com.valefor.wowapiframe.model;

public class Realm {
    private String type;
    private String name;
    private String slug;

    public String getType() { return this.type; }
    public String getName() { return this.name; }
    public String getSlug() { return this.slug; }

    public void setType(String type) { this.type = type; }
    public void setName(String name) { this.name = name; }
    public void setSlug(String slug) { this.slug = slug; }

    Realm() {
    }
}
