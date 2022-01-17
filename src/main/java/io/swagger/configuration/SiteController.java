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

    @GetMapping(value = "/orders")
    public ModelAndView getOrders(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        Cookie[] cookies = request.getCookies();

        String email = null;
        String password = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("emailO")) {
                    email = cookie.getValue();
                }
                else if (cookie.getName().equals("passwordO")) {
                    password = cookie.getValue();
                }
            }
        }

        if(email != null && password != null){
            try {
                HttpURLConnection con;
                URL url = new URL("http://localhost:9191/api/checkProfile");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("email", email);
                con.setRequestProperty("password", password);
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                con.setDoInput(true);

                int responsecode = con.getResponseCode();
                con.disconnect();

                if(responsecode == 200){
                    return new ModelAndView("site/orders.html");
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

    @GetMapping(value = "/products")
    public ModelAndView getProducts(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        Cookie[] cookies = request.getCookies();
        String email = null;
        String password = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("emailO")) {
                    email = cookie.getValue();
                }
                else if (cookie.getName().equals("passwordO")) {
                    password = cookie.getValue();
                }
            }
        }
        if(email != null && password != null){
            try {
                HttpURLConnection con;
                URL url = new URL("http://localhost:9191/api/checkProfile");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("email", email);
                con.setRequestProperty("password", password);
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                con.setDoInput(true);

                int responsecode = con.getResponseCode();
                con.disconnect();

                if(responsecode == 200){
                    return new ModelAndView("site/products.html");
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

        if(requestParams.containsKey("password") && requestParams.containsKey("email")) {
            try {
                HttpURLConnection con;
                URL url = new URL("http://localhost:9191/api/checkProfile");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("email", requestParams.get("email"));
                con.setRequestProperty("password", requestParams.get("password"));
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                con.setDoInput(true);

                if (con.getResponseCode() == 200) {
                    Cookie cookie = new Cookie("emailO", requestParams.get("email"));
                    cookie.setMaxAge(7 * 24 * 6000);

                    Cookie cookie1 = new Cookie("passwordO", requestParams.get("password"));
                    cookie1.setMaxAge(7 * 24 * 6000);

                    response.addCookie(cookie);
                    response.addCookie(cookie1);
                    response.sendRedirect("/orders");
                } else {
                    response.sendRedirect("/register");
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
                requestParams.containsKey("name")) {
            try {
                HttpURLConnection con;
                URL url = new URL("http://localhost:9191/api/profile");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestProperty("name", requestParams.get("name"));
                con.setRequestProperty("email", requestParams.get("email"));
                con.setRequestProperty("password", requestParams.get("password"));
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);

                int response1 = con.getResponseCode();
                con.disconnect();

                if(response1 != 200){
                    response.sendRedirect("/register");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Cookie cookie = new Cookie("emailO", requestParams.get("email"));
        cookie.setMaxAge(7 * 24 * 6000);

        Cookie cookie1 = new Cookie("passwordO", requestParams.get("password"));
        cookie1.setMaxAge(7 * 24 * 6000);

        response.addCookie(cookie);
        response.addCookie(cookie1);

        try {
            response.sendRedirect("/orders");
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