package com.project.shop.services.declarations;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FileService {
    void save(MultipartFile multipartFile, HttpServletRequest httpRequest) throws IOException;
}
