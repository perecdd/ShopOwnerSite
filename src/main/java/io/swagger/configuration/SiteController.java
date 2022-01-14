package io.swagger.configuration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

@RestController
public class SiteController {

    @GetMapping(value = "/")
    public ModelAndView getMain() {
        System.out.println("Main");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("site/main.html");
        return modelAndView;
    }

    @RequestMapping(value = "/profile")
    public ModelAndView getProfile(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        ModelAndView modelAndView = new ModelAndView();
        Cookie[] cookies = request.getCookies();

        String email = null;
        String password = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    email = cookie.getValue();
                }
                else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }
        if(email != null && password != null){
            try {
                HttpURLConnection con;
                URL url = new URL("http://localhost:4040/login");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestProperty("email", email);
                con.setRequestProperty("password", password);
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                con.setDoInput(true);

                int responsecode = con.getResponseCode();
                con.disconnect();

                if(responsecode == 200){
                    System.out.println("TEST");
                    return new ModelAndView("site/profile.html");
                }
                else{
                    return new ModelAndView("/register");
                }
            }
            catch (Exception e){
                e.printStackTrace();
                return new ModelAndView("/register");
            }
        }
        else{
            return new ModelAndView("/register");
        }
    }

    @RequestMapping(value = "/auth", method=RequestMethod.GET)
    public void postAuth(@RequestParam Map<String,String> requestParams, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        System.out.println("AUTH");
        System.out.println(requestParams.get("email") + " " + requestParams.get("password"));
        if(requestParams.containsKey("password") && requestParams.containsKey("email")) {
            try {
                HttpURLConnection con;
                URL url = new URL("http://localhost:4040/login");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestProperty("email", requestParams.get("email"));
                con.setRequestProperty("password", requestParams.get("password"));
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                con.setDoInput(true);

                if (con.getResponseCode() == 200) {
                    Cookie cookie = new Cookie("email", requestParams.get("email"));
                    cookie.setMaxAge(7 * 24 * 6000);

                    Cookie cookie1 = new Cookie("password", requestParams.get("password"));
                    cookie1.setMaxAge(7 * 24 * 6000);

                    response.addCookie(cookie);
                    response.addCookie(cookie1);
                    //return new ModelAndView("site/profile.html");
                    response.sendRedirect("/profile");
                } else {
                    response.sendRedirect("/");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(path="/reg",method=RequestMethod.GET)
    public void postRegister(@RequestParam Map<String,String> requestParams, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        if(requestParams.containsKey("email") &&
                requestParams.containsKey("password") &&
                requestParams.containsKey("name") &&
                requestParams.containsKey("surname") &&
                requestParams.containsKey("city") &&
                requestParams.containsKey("country") &&
                requestParams.containsKey("street") &&
                requestParams.containsKey("house") &&
                requestParams.containsKey("flat") &&
                requestParams.containsKey("phone")) {
            try {
                JSONObject newUser = new JSONObject();
                {
                    JSONObject address = new JSONObject();
                    address.put("country", requestParams.get("country"));
                    address.put("city", requestParams.get("city"));
                    address.put("street", requestParams.get("street"));
                    address.put("house", requestParams.get("house"));
                    address.put("flat", requestParams.get("flat"));
                    newUser.put("address", address);
                }
                newUser.put("basket", new JSONArray());
                newUser.put("email", requestParams.get("email"));
                newUser.put("id", 0);
                newUser.put("name", requestParams.get("name"));
                newUser.put("password", requestParams.get("password"));
                newUser.put("phone", requestParams.get("phone"));
                newUser.put("surname", requestParams.get("surname"));
                StringBuilder stringBuilder = new StringBuilder(newUser.toString());

                HttpURLConnection con;
                URL url = new URL("http://localhost:4040/login");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);

                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(con.getOutputStream())), true);
                printWriter.println(newUser.toString());
                int response1 = con.getResponseCode();
                printWriter.close();
                con.disconnect();

                if(response1 != 200){
                    response.sendRedirect("/register");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Cookie cookie = new Cookie("email", requestParams.get("email"));
        cookie.setMaxAge(7 * 24 * 6000);

        Cookie cookie1 = new Cookie("password", requestParams.get("password"));
        cookie1.setMaxAge(7 * 24 * 6000);

        response.addCookie(cookie);
        response.addCookie(cookie1);

        try {
            response.sendRedirect("/profile");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/register")
    public ModelAndView getRegister(@RequestParam Map<String,String> requestParams, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("site/register.html");
        return modelAndView;
    }
}