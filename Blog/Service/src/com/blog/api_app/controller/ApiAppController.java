package com.blog.api_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.api_app.service.ApiAppService;
import com.blog.base.controller.BaseController;


@Controller
@EnableAsync
@RequestMapping(value="/api_app")
public class ApiAppController  extends BaseController {

	@Autowired
	private ApiAppService apiAppService;


}
