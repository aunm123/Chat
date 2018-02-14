package com.controller;

import com.pojo.ResultData;
import com.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Controller
@RequestMapping("/upload")
public class UploadFileController {

    @Autowired
    private UploadService uploadService ;

    @RequestMapping("/upladimg")
    @ResponseBody
    public ResultData upladFile(MultipartFile file) {
        String fileUrlStr = uploadService.uploadPicture(file);
        if (StringUtils.isEmpty(fileUrlStr)){
            return ResultData.FaileResultData("上传失败",501);
        }else {
            HashMap hashMap = new HashMap();
            hashMap.put("url",fileUrlStr);
            return ResultData.SuccessResultData(hashMap);
        }

    }
}
