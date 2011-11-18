package com.valefor.wowapiframe.model;

public class Realm implements Comparable<Realm> {
    private String type;
    private String name;
    private String slug;

    public String getType() { return this.type; }
    public String getName() { return this.name; }
    public String getSlug() { return this.slug; }

    public int compareTo(Realm r) {
        return this.name.compareTo(r.getName());
    }
}
