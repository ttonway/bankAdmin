package com.psbc.controller;

import com.psbc.pojo.AdminUser;
import com.psbc.pojo.PosterImage;
import com.psbc.service.AdminUserService;
import com.psbc.service.PosterService;
import com.psbc.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/poster")
public class PosterController {

	@Value("${fileserver.path}")
	private String fileRootPath;

	@Value("${fileserver.httpurl}")
	private String fileRootUrl;

	@Autowired
	private PosterService posterService;

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<PosterImage> list = posterService.selectAll();
			map.put("code", 0);
			map.put("data", list);
			return map;
		} catch (Exception e) {
			map.put("code", 1);
			return map;
		}
	}

}
