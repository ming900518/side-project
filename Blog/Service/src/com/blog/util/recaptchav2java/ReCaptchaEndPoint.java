package com.blog.util.recaptchav2java;

public class ReCaptchaEndPoint {
	    static final String URL_PROTOCOL_HOST = "https://www.google.com";
	    static final String URL_QUERY_PART = "/recaptcha/api/siteverify";
	    private static final String URL_ABSOLUTE = URL_PROTOCOL_HOST + URL_QUERY_PART;

	    private static final String POST_PARAM_SECRET = "secret";
	    private static final String POST_PARAM_TOKEN = "response";
	    private final String secret;

	    ReCaptchaEndPoint(String secret) {
	        this.secret = secret;
	    }

	    boolean verify(String token) {
	        String response = Http.post(getAbsoluteUrl(), createUrlParameters(token));
	        boolean success = new ReCaptchaJson(response).isSuccess();
	        return success;
	    }

	    private String createUrlParameters(String token) {
	        return POST_PARAM_SECRET + "=" + secret + "&" +
	            POST_PARAM_TOKEN + "=" + token;
	    }

	    protected String getAbsoluteUrl() {
	        return URL_ABSOLUTE;
	    }
}
