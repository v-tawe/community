package com.kyss.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.kyss.community.dto.FileDTO;
import com.kyss.community.enums.FileUploadEnum;
import com.kyss.community.provider.AzureProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName FileUpload
 * @Description TODO
 * @Author davidt
 * @Date 7/3/2020 3:50 PM
 * @Version 1.0
 **/
@Controller
public class FileUploadController {

    @Autowired
    private AzureProvider azureProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public String upload(@RequestParam(value="editormd-image-file")MultipartFile file) {
        String fileName = file.getOriginalFilename();
        FileDTO fileDTO = new FileDTO();
        try {
            azureProvider.createBlob(file.getInputStream(), file.getSize(), fileName);
            fileDTO.setSuccess(FileUploadEnum.UPLOAD_SUCCESS.getSuccess());
            fileDTO.setMessage(FileUploadEnum.UPLOAD_SUCCESS.getMessage());
            fileDTO.setUrl(azureProvider.getBlob(fileName));
        } catch (IOException e) {
            fileDTO.setSuccess(FileUploadEnum.UPLOAD_FILED.getSuccess());
            fileDTO.setMessage(FileUploadEnum.UPLOAD_FILED.getMessage());
            fileDTO.setUrl(azureProvider.getBlob(fileName));
            e.printStackTrace();
        }
        return JSONObject.toJSONString(fileDTO);
    }
}
