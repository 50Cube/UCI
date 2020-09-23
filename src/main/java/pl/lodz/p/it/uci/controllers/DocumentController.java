package pl.lodz.p.it.uci.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.lodz.p.it.uci.exceptions.FileException;
import pl.lodz.p.it.uci.services.DocumentService;

@RestController
@AllArgsConstructor
public class DocumentController {

    private DocumentService documentService;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("SwaggerUI test");
    }

    @PostMapping(value = "/document", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadFile(@RequestPart(name = "file") MultipartFile file) {
        try {
            documentService.uploadFile(file);
        } catch (FileException e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok("File uploaded successfully");
    }
}
