package com.valefor.wowapiframe.model;

import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

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

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json.toString()).getAsJsonObject().getAsJsonArray("realms");

        Iterator<JsonElement> i = array.iterator();
        while(i.hasNext()) {
            Realm realm = gson.fromJson(i.next(), Realm.class);
            this.realms.add(realm);
        }

        System.out.println("Loaded " + this.size() + " realms");
    }
}
