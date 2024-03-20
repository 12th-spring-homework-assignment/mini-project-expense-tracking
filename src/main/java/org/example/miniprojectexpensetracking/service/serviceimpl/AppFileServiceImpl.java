package org.example.miniprojectexpensetracking.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.example.miniprojectexpensetracking.exception.BadRequestException;
import org.example.miniprojectexpensetracking.exception.NotFoundException;
import org.example.miniprojectexpensetracking.model.AppFile;
import org.example.miniprojectexpensetracking.service.AppFileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppFileServiceImpl implements AppFileService {

    private static final Path PATH = Paths.get("src/main/resources/images");

    @Override
    public AppFile upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        if (fileName.contains(".jpg") || fileName.contains(".png") || fileName.contains(".jpeg")){
            fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
            if (!Files.exists(PATH)){
                Files.createDirectories(PATH);
            }
            Files.copy(file.getInputStream(), PATH.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return new AppFile(fileName, file.getContentType(), file.getSize());
        }
        throw new BadRequestException("File must be contain jpg, png, jpeg");
    }

    @Override
    public Resource findFileByFileName(String fileName) throws IOException {
        if (!(fileName.contains(".jpg") || fileName.contains(".png") || fileName.contains(".jpeg"))){
            throw new BadRequestException("File must be contain jpg, png, jpeg");
        }
        Path path = Paths.get("src/main/resources/images/" + fileName);
        if (Files.notExists(path)){
            throw new NotFoundException("File not founded");
        }
        return new ByteArrayResource(Files.readAllBytes(path));
    }
}
