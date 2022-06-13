package main.java.fr.verymc.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class HTTPUtils {

    public static String baseUrl = "http://127.0.0.1:8080";

    public static ArrayList readFromUrl(String url){
        try(java.io.InputStream is = new java.net.URL(url).openStream()) {
            String contents = new String(is.readAllBytes());
            ArrayList<String> toReturn = new ArrayList();
            while(contents.contains("{")){
                int toGo = contents.indexOf("}");
                String temp = contents.substring(0, toGo+1);
                toReturn.add(temp);
                contents=contents.replace(temp,"");
                System.out.println(temp);
            }
            return toReturn;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void postMethod(String postUrl, String jsonInput) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl + postUrl);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        System.out.println(jsonInput);
        StringEntity stringEntity = new StringEntity(jsonInput);
        httpPost.setEntity(stringEntity);

        System.out.println("Executing request " + httpPost.getRequestLine());
        HttpResponse response = httpclient.execute(httpPost);

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        //Throw runtime exception if status code isn't 200

        if (response.getStatusLine().getStatusCode() != 200) {
            if (response.getStatusLine().getStatusCode() == 201) return;
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        //Create the StringBuffer object and store the response into it.
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println("Response : \n"+result.append(line));
        }
    }
}
