package app.service;

import app.dto.UploadForm;
import app.model.User;
import app.repository.interfaces.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

@Service
public class FilesUploadServiceImpl {

    private static final String UPLOADED_FOLDER = "/home/yuliya/IdeaProjects/social-network/src/main/webapp/static/";
    private static final String PATH_IN_DB = "/static/";

    @Autowired
    UsersRepository usersRepository;

    public void upload(UploadForm form) {

        MultipartFile file = form.getPhoto();

        if (!file.isEmpty()) {

            String storageFileName = createFileName(UPLOADED_FOLDER + file.getOriginalFilename());

            try {

                byte[]bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + storageFileName);
                Files.write(path, bytes);

            } catch (IOException e) {
                e.printStackTrace();
            }

            usersRepository.updateAvaPath(form.getUserId(), PATH_IN_DB + storageFileName);

        }

    }


    private String createFileName(String sourceURL) {

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
