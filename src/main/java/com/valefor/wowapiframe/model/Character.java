package com.valefor.wowapiframe.model;

// Java Imports
import java.util.List;

// HttpClient Imports
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

// GSON Imports
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

// Model Imports
import com.valefor.wowapiframe.model.Guild;
import com.valefor.wowapiframe.model.Talents;
import com.valefor.wowapiframe.model.Progression;

public class Character {
    private String name;
    private String realm;
    private int level;
    private int gender;
    @SerializedName("class") private int cclass;
    private Guild guild;
    private List<Talents> talents;
    private Progression progression;

    public String getName() { return this.name; }
    public String getRealm() { return this.realm; }
    public int getLevel() { return this.level; }
    public int getGender() { return this.gender; }
    public int getCclass() { return this.cclass; }
    public Guild getGuild() { return this.guild; }
    public List<Talents> getTalents() { return this.talents; }
    public Progression getProgression() { return this.progression; }

    /**
     * Loads and returns a new Character
     *
     * @param realm Realm that a character resides on (name or slug)
     * @param name The name of the character to be loaded
     * @return The loaded Character object
     */
    public static Character load(String realm, String name) {
        Gson gson = new Gson();
        Character c = gson.fromJson(getCharacterJson(realm, name), Character.class);

        System.out.println("Loaded " + c.getName() + " from " + c.getRealm());
        return c;
    }

    /**
     * Calls out to the Blizzard API and returns a string containing the
     * response
     *
     * @return the JSON response as a String
     */
    private static String getCharacterJson(String realm, String name) {
        String json = "";

        try {
            HttpGet request = new HttpGet("http://us.battle.net/api/wow/character/" + realm + "/" + name + "?fields=progression,talents,guild");
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                json = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            // Oh well... no realms for now
        }

        return json;
    }
    
}
