package com.valefor.wowapiframe.model;

import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

import org.apache.http.client.methods.HttpGet;
import com.valefor.wowapiframe.model.Realm;

public class RealmList {
    private List<Realm> realms;

    public List<Realm> all() { return this.realms; }
    public void addRealm(Realm r) { realms.add(r); }
    public int size() { return realms.size(); }

    // Instantiate a new realm list and populate it from the wow api
    public void init() {
        // Load from the json
        this.realms = this.loadFromJson(this.getRealmJson());
    }

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

    private List<Realm> loadFromJson(String json) {
        List<Realm> realms = new Vector<Realm>();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json.toString()).getAsJsonObject().getAsJsonArray("realms");

        Iterator<JsonElement> i = array.iterator();
        while(i.hasNext()) {
            Realm realm = gson.fromJson(i.next(), Realm.class);
            realms.add(realm);
        }

        System.out.println("Loaded " + realms.size() + " realms");

        return realms;
    }
}
