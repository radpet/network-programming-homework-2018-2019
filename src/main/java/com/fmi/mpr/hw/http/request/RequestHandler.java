package com.fmi.mpr.hw.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private Socket client;

    public RequestHandler(Socket client) {
        this.client = client;
    }

    public void run() {
        read();
    }


    private void read() {
        try {
            BufferedReader bis = new BufferedReader(new
                    InputStreamReader(client.getInputStream()));
            String inputLine;
            while ((inputLine = bis.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
