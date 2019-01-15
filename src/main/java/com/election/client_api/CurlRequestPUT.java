package com.election.client_api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurlRequestPUT {

    public static void main(String[] args) throws IOException, JSONException {
        //String url = "http://localhost:8099/api/team/5";
        String url = "http://localhost:8099/api/team/reject/5";
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("PUT");

        JSONObject mem = new JSONObject();

        //{"teamId":"5","participantId":90,"participantIdentifier":"Player 55 fn55, ln55","cap":true} or false
        mem.put("teamId","5");
        mem.put("participantId", "90");
        mem.put("participantIdentifier", "Player 55 fn55, ln55");
        //mem.put("cap", "true");
        mem.put("cap", "false");

        System.out.println(mem);

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(mem.toString());

        wr.flush();

        //display what returns the PUT request
        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            System.out.println("" + sb.toString());
        } else {
            System.out.println(con.getResponseMessage());
        }

        wr.close();
    }
}
