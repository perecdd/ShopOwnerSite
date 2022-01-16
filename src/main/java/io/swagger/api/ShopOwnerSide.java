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

    public static JSONArray getTickets(String email, String password){
        try {
            URL url = new URL (ip + ":" + port + "/ticketCompany");

            con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("email", email);
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

    public static boolean updateTicket(String email, String password, Integer ticket, String status) throws Exception {
            URL url = new URL(ip + ":" + port + "/ticketCompany");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "ShopOwnerApplication");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("email", email);
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

    public static JSONArray getAllProductsByEmail(String email){
        try {
            URL url = new URL (ip + ":" + port + "/storageCompany");

            con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("companyEmail", email);
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

            System.out.println(jsonArray.toString());

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

    public static boolean checkCompany(String email, String password){
        try {
            System.out.println(email + " " + password);

            URL url = new URL (ip + ":" + port + "/checkStorage");

            con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("email", email);
            con.setRequestProperty("password", password);
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setDoInput(true);

            int responseCode = con.getResponseCode();
            con.disconnect();

            return responseCode == 200;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateProducts(String email, String password, JSONObject products) throws Exception {
        URL url = new URL(ip + ":" + port + "/storage");
        con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("email", email);
        con.setRequestProperty("password", password);
        con.setRequestMethod("PUT");
        con.setDoOutput(true);
        con.setDoInput(true);

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(con.getOutputStream())), true);
        out.println(products.toString());

        int responseCode = con.getResponseCode();
        con.disconnect();
        return responseCode == 202;
    }

    public static boolean registerCompany(String email, String password) throws Exception {
        URL url = new URL(ip + ":" + port + "/storage");
        con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "ShopOwnerApplication");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("email", email);
        con.setRequestProperty("password", password);
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);

        int responseCode = con.getResponseCode();
        con.disconnect();
        return responseCode == 201;
    }
}
