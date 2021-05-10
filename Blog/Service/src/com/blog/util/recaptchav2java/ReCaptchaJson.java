package com.blog.util.recaptchav2java;

public class ReCaptchaJson {
	private static final String REGEX_IS_SUCCESS = "(?s).*\"success\".*:.*true.*";

    private String json;

    ReCaptchaJson(String json) {
        this.json = json;
    }

    boolean isSuccess() {
        return json.matches(REGEX_IS_SUCCESS);
    }
}
