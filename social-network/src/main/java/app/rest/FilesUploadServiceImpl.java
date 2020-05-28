package ru.itis.socialnetworkboot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.socialnetworkboot.dto.UploadForm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FilesUploadServiceImpl {

    private static final String UPLOADED_FOLDER = "/home/yuliya/uploads/";

    public String upload(UploadForm form) {

        String storageFileName = "";

        MultipartFile file = form.getPhoto();

        if (!file.isEmpty()) {

            storageFileName = createFileName(UPLOADED_FOLDER + file.getOriginalFilename());

            try {

                byte[]bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + storageFileName);
                Files.write(path, bytes);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return storageFileName;

    }


    private String createFileName(String sourceURL) {

        sourceURL = sourceURL.replace(" ", "_");

        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1);
        fileName = fileName.replace(" ", "_");
        File file = new File(sourceURL);
        String s;

        if(file.exists()) {

            String name = fileName.substring(0, fileName.indexOf("."));
            String extension = fileName.substring(fileName.indexOf("."));
            char c = name.charAt(name.length() - 1);

            if(c >= '0' && c <= '9') {
                int n = Integer.parseInt("" + c);
                s = name.substring(0, name.lastIndexOf(String.valueOf(n))) + (n + 1) + extension;
            }
            else {
                s = name + "_1" + extension;
            }

            return createFileName(s);

        }

        else {
            return fileName;
        }

    }

}
