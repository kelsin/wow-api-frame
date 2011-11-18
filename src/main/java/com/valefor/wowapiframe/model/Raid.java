package com.valefor.wowapiframe.model;

// Java Imports
import java.util.List;

// Model Imports
import com.valefor.wowapiframe.model.Boss;

public class Raid {
    private String name;
    private int normal;
    private int heroic;
    private List<Boss> bosses;

    public String getName() { return this.name; }
    public int getNormal() { return this.normal; }
    public int getHeroic() { return this.heroic; }
    public List<Boss> getBosses() { return this.bosses; }

    public String getSlug() {
        return this.name.toLowerCase().replaceAll("-|'","").replace(' ', '-');
    }
}
