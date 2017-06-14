package com.psbc.controller;

import com.psbc.pojo.PosterImage;
import com.psbc.service.PosterService;
import com.psbc.util.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/poster")
public class PosterController {
    private static Logger logger = Logger.getLogger(PosterController.class);

    @Value("${fileserver.path}")
    private String fileRootPath;

    @Autowired
    private PosterService posterService;


    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam MultipartFile file, String posterName, String loanType) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (!file.isEmpty()) {
            try {
                UUID uuid = UUID.randomUUID();
                File target = new File(fileRootPath, uuid.toString());
                file.transferTo(target);

                PosterImage poster = new PosterImage();
                poster.setFileName(uuid.toString());
                poster.setPosterName(posterName);
                poster.setLoanType(loanType);
                poster.setContentType(file.getContentType());
                String timeStr = Utils.currentTimeStr();
                poster.setCreatetime(timeStr);
                poster.setUpdatetime(timeStr);
                if (posterService.insert(poster) == 1) {
                    map.put("code", 0);
                } else {
                    map.put("code", 1);
                }
            } catch (Exception e) {
                logger.error("upload fail.", e);
                map.put("code", 1);
            }
        }
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Long posterId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            posterService.deleteByPrimaryKey(posterId);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            map.put("code", 1);
            return map;
        }
    }
}