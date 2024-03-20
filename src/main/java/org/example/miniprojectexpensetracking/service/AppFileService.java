package org.example.miniprojectexpensetracking.service;

import org.example.miniprojectexpensetracking.model.AppFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AppFileService {
    AppFile upload(MultipartFile file) throws IOException;

    Resource findFileByFileName(String fileName) throws IOException;
}
