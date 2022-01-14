package io.swagger.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ShopOwnerSide {
    public final static Integer port = 15676;
    public final static String ip = "http://localhost"; // TODO http://shopownerside
    public static HttpURLConnection con;

    public static JSONArray getTickets(Integer companyid, String password){
        try {
            URL url = new URL (ip + ":" + port + "/ticketCompany");

            con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("companyid", String.valueOf(companyid));
            con.setRequestProperty("password", password);
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setDoInput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }

            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(sb.toString());

            int responseCode = con.getResponseCode();
            con.disconnect();

            if(responseCode == 200) {
                return jsonArray;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateTicket(Integer companyid, String password, Integer ticket, String status) throws Exception {
            URL url = new URL(ip + ":" + port + "/ticketCompany");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "ShopOwnerApplication");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("companyid", String.valueOf(companyid));
            con.setRequestProperty("password", password);
            con.setRequestProperty("ticket", String.valueOf(ticket));
            con.setRequestProperty("status", status);
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);

            int responseCode = con.getResponseCode();
            con.disconnect();
            return responseCode == 200;
    }

    public static JSONArray getAllProductsByCompanyid(Integer companyid){
        try {
            URL url = new URL (ip + ":" + port + "/storage");

            con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("companyID", String.valueOf(companyid));
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setDoInput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(sb.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("products");

            int responseCode = con.getResponseCode();
            con.disconnect();

            if(responseCode == 200) {
                return jsonArray;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateProducts(Integer companyid, String password, JSONObject products) throws Exception {
        URL url = new URL(ip + ":" + port + "/storage");
        con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("companyid", String.valueOf(companyid));
        con.setRequestProperty("password", password);
        con.setRequestMethod("PUT");
        con.setDoOutput(true);
        con.setDoInput(true);

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(con.getOutputStream())), true);
        out.println(products.toString());

        int responseCode = con.getResponseCode();
        con.disconnect();
        return responseCode == 200;
    }

    public static boolean registerCompany(Integer companyid, String password) throws Exception {
        URL url = new URL(ip + ":" + port + "/storage");
        con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "ShopOwnerApplication");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("companyid", String.valueOf(companyid));
        con.setRequestProperty("password", password);
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);

        int responseCode = con.getResponseCode();
        con.disconnect();
        return responseCode == 201;
    }
}
