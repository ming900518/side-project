package com.mingchang.BlogService.service;

import com.mingchang.BlogService.model.LoginOutput;
import com.mingchang.BlogService.model.request.LoginBackendRequest;

public interface BlogService {

    LoginOutput login(LoginBackendRequest loginBackendRequest);

}
