package com.project.shop.services;

import com.project.shop.services.declarations.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void save(MultipartFile multipartFile, HttpServletRequest httpRequest) throws IOException {
        if (multipartFile.isEmpty())
            return;

        String filePath = httpRequest.getServletContext().getRealPath("/uploads");
        File f1 = new File(filePath + "/" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(f1);
    }
}
