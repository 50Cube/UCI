package pl.lodz.p.it.uci.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.lodz.p.it.uci.exceptions.FileException;

import java.util.Objects;

@Log
@Service
public class DocumentService {

    public void uploadFile(MultipartFile file) throws FileException {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if(file.isEmpty()) throw new FileException("File is empty");
        log.info(filename);
    }
}
