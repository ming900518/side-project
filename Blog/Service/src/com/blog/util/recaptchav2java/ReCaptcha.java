package com.blog.util.recaptchav2java;

public class ReCaptcha {
	private final String secret = "6LcT6FQUAAAAANTvfaj3WIOkPvo3MpuDa_BAyLtB";

    /**
     * Creates a new ReCaptcha service using a specific secret key.
     *
     * @param secret the server-side secret. Most not be {@code null}!
     */
//    public ReCaptcha(String secret) {
//        this.secret = secret;
//    }

    /**
     * Validates a response token generate by Google's reCAPTCHA V2 client widget.
     *
     * @param captchaResponseToken the result of a captcha challenge.
     * @return {@code true} if the challenge was successful, otherwise {@code false}
     * @throws ReCaptchaException when something technically went wrong during captcha validation.
     */
    public boolean isValid(String captchaResponseToken) {
        return createReCaptchaEndPoint().verify(captchaResponseToken);
    }

    protected ReCaptchaEndPoint createReCaptchaEndPoint() {
        return new ReCaptchaEndPoint(secret);
    }
    
}
