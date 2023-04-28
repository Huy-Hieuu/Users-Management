package API_Demo.Springboot.Services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface iStorageService {
    public String storeFile(MultipartFile file);
    public Stream<Path> loadAll(); //load all file inside a folder
    public byte[] readFileContent(String fileName); //array of byte to store all byte of file
    public void deleteAllFiles();
}
