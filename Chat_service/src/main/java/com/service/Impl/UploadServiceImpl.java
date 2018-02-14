package com.service.Impl;

import com.service.UploadService;
import com.util.IDUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {
    public String uploadPicture(MultipartFile picture) {
        String OrginalName = picture.getOriginalFilename();
        String newName = IDUtils.genImageName();
        newName = newName + OrginalName.substring(OrginalName.lastIndexOf("."));


        return newName;
    }
}
