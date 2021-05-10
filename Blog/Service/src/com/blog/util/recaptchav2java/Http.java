package com.blog.util.recaptchav2java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Function;

public class Http {
	private Http() {}

    static String post(String url, String urlParameters) {
        return withConnectionTo(url, connection -> {

            sendPostRequest(connection, urlParameters);

            return receiveResponse(connection);
        });
    }

    private static String withConnectionTo(String url, Function<HttpURLConnection, String> runnable) {
        HttpURLConnection con = null;
        try {
            con = openConnection(url);
            return runnable.apply(con);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    private static HttpURLConnection openConnection(String url) {
        try {
            return (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            throw new ReCaptchaException("Unable to create URL for posting", e);
        }
    }

    private static void sendPostRequest(HttpURLConnection con, String bodyParams) {
        try {
            sendPostRequestWithExceptions(con, bodyParams);
        } catch (IOException e) {
            throw new ReCaptchaException("I/O error while sending the POST request", e);
        }
    }

    private static void sendPostRequestWithExceptions(HttpURLConnection con, String parameters) throws IOException {
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(parameters);
            wr.flush();
        }
    }

    private static String receiveResponse(HttpURLConnection con) {
        try {
            return receiveResponseWithExceptions(con);
        } catch (IOException e) {
            throw new ReCaptchaException("I/O error receiving the response ", e);
        }
    }

    private static String receiveResponseWithExceptions(HttpURLConnection con) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String response = toString(in);
            return response;
        }
    }

    private static String toString(BufferedReader in) throws IOException {
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        return response.toString();
    }
}
