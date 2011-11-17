package com.valefor.wowapiframe.model;

import java.util.List;
import java.util.Vector;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.simple.*;

import com.valefor.wowapiframe.model.Realm;

public class RealmList {
    private List<Realm> realms;

    public List<Realm> all() { return this.realms; }
    public void addRealm(Realm r) { realms.add(r); }
    public int size() { return realms.size(); }

    // Instantiate a new realm list and populate it from the wow api
    public void init() {
        this.realms = new Vector<Realm>();
        StringBuffer json = new StringBuffer();

        try {
            URL url = new URL("http://us.battle.net/api/wow/realm/status");

            BufferedReader buff = new BufferedReader(new InputStreamReader((InputStream) url.getContent()));
            String line = buff.readLine();
            while (line != null) {
                json.append(line + "\n");
                line = buff.readLine();
            }
        } catch (Exception e) {
        }

        JSONArray ary = (JSONArray)((JSONObject)JSONValue.parse(json.toString())).get("realms");
        for(Object o : ary) {
            JSONObject r = (JSONObject)o;
            Realm realm = new Realm();
            realm.setType((String)r.get("type"));
            realm.setName((String)r.get("name"));
            realm.setSlug((String)r.get("slug"));
            this.addRealm(realm);
        }

        System.out.println("Loaded " + this.size() + " realms");
    }
}
