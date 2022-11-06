package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import java.awt.print.Pageable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentService studentService;

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }
    Logger logger = LoggerFactory.getLogger(AvatarService.class);

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Method called uploadAvatar");
        Student student = studentService.getStudent(studentId);
        String avatarsDir = null;
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = avatarRepository.findByStudentId(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(long studentId) {
        logger.info("Method called findAvatar");
        return avatarRepository.findByStudentId(studentId);
    }

    private String getExtensions(String fileName) {
        logger.info("Method called getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Collection<Avatar> getAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Method called getExtensions");
        var pageRequest = PageRequest.of(pageNumber,pageSize);
        return avatarRepository.getAvatar(pageRequest);
    }
}
