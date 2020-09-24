package pl.lodz.p.it.uci.services;


import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.lodz.p.it.uci.exceptions.FileException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

@Log
@Service
public class DocumentService {

    private Element element;

    public void uploadFile(MultipartFile file) throws FileException {
        if(file.isEmpty()) throw new FileException("File is empty");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if(!extension.equals("xml")) throw new FileException("Wrong file extension, .xml is required");

        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String inline;
            while ((inline = bufferedReader.readLine()) != null)
                stringBuilder.append(inline);

            log.info("file content: " + stringBuilder.toString());
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(new ByteArrayInputStream(stringBuilder.toString().getBytes()));

            if(document.getRootElement().getChildren().stream().noneMatch(element -> element.getName().equals("Signature")))
                throw new FileException("File doest not contain signature element");


            try {
                findElementRecursively(document.getRootElement(), "DaneZPOsobyFizycznej");
            } catch (RuntimeException e) {
                // break recursive loop
            }
            if(this.element != null) {
                for(Element element : this.element.getChildren())
                    log.info(element.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findElementRecursively(Element elementParam, String name) {
        for(Element listElement : elementParam.getChildren()) {
            if(listElement.getName().equals(name)) {
                this.element = listElement;
                throw new RuntimeException();
            }
            findElementRecursively(listElement, name);
        }
    }
}
