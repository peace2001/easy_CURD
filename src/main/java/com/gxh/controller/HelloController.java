package com.gxh.controller;

import com.gxh.config.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public Result helloworld(){
		return Result.ok().data("访问成功",null);
	}
}
