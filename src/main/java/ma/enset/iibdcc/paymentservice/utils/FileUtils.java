package ma.enset.iibdcc.paymentservice.utils;

import lombok.AllArgsConstructor;
import ma.enset.iibdcc.paymentservice.config.FileConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@EnableConfigurationProperties(FileConfig.class)
@AllArgsConstructor
public class FileUtils {

    private FileConfig fileConfig;

    public String upload(MultipartFile file) throws IOException {

        Path folderPath = Paths.get(fileConfig.path());
        if(isFolderExist(folderPath).equals(Boolean.FALSE)){
            Files.createDirectories(folderPath);
        }
        Path filePath = Paths.get(fileConfig.path(), file.getName() +".pdf");
        Files.copy(file.getInputStream(), filePath);

        return filePath.toUri().toString();
    }

    private Boolean isFolderExist(Path path){
        return  Files.exists(path);
    }
}
