package com.valefor.wowapiframe.model;

// Java Imports
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

// HttpClient Imports
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

// GSON Imports
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

// Model Imports
import com.valefor.wowapiframe.model.Realm;

/**
 * Class which knows how to load a list of realms from the Blizzard WoW API's
 *
 * When init() is called it makes a request out to http://us.battle.net/api/wow/realm/status
 *
 * @author Christopher Giroir <kelsin@valefor.com>
 */
public class RealmList {
    /**
     * The Map of realms
     */
    private Map<String,Realm> realms;

    /**
     * Return the list of Realms
     *
     * @return A List of all of the known Realms
     */
    public Collection<Realm> all() { return this.realms.values(); }

    /**
     * Add a realm to this RealmList
     *
     * @param r The realml to add
     */
    public void addRealm(Realm r) { this.realms.put(r.getSlug(), r); }

    /**
     * Finds a realm by the slug
     *
     * @param slug The slug of the realm to find
     */
    public Realm findBySlug(String slug) { return realms.get(slug); }

    /**
     * Returns the total number of Realms in the list
     *
     * @return the total number of Realms in the list
     */
    public int size() { return realms.size(); }

    /**
     * Creates a new empty RealmList
     */
    RealmList() {
        this.realms = new TreeMap<String,Realm>();
    }

    /**
     * Calls out to the Blizzard API and fills the realm list
     */
    public void init() {
        this.loadFromJson(this.getRealmJson());
    }

    /**
     * Calls out to the Blizzard API and returns a string containing the
     * response
     *
     * @return the JSON response as a String
     */
    private String getRealmJson() {
        String json = "";

        try {
            HttpGet request = new HttpGet("http://us.battle.net/api/wow/realm/status");
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

    /**
     * Takes a json string and loads it into this.realms
     *
     * @param json The String of JSON containing the realm data
     */
    private void loadFromJson(String json) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json.toString()).getAsJsonObject().getAsJsonArray("realms");

        Iterator<JsonElement> i = array.iterator();
        while(i.hasNext()) {
            this.addRealm(gson.fromJson(i.next(), Realm.class));
        }

        System.out.println("Loaded " + this.size() + " realms");
    }
}
