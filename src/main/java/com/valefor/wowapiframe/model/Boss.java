package com.valefor.wowapiframe.model;

public class Boss {
    private String name;
    private int normalKills;
    private int heroicKills;

    public String getName() { return this.name; }
    public int getNormalKills() { return this.normalKills; }
    public int getHeroicKills() { return this.heroicKills; }

    public String getSlug() {
        return this.name.toLowerCase().replaceAll("-|'","").replace(' ', '-');
    }
}
