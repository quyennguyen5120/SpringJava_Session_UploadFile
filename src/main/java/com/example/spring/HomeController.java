package com.example.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class HomeController {
    @Value("${config.upload_folder}")
    String UPLOAD_FOLDER;

    @GetMapping("/index")
    public String index(HttpSession session){
        session.setAttribute("abcz","abc");
        session.setMaxInactiveInterval(10);
        return "index";
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file){
        String realativeFilePath = null;
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        String subFolder = month +"_"+year;
        String fullUploadDir = UPLOAD_FOLDER + subFolder;
        File checkDir = new File(fullUploadDir);
        if(checkDir.exists() == true || checkDir.isFile() == true){
            checkDir.mkdir();
        }
        try{
            realativeFilePath = subFolder + Instant.now().getEpochSecond() + file.getOriginalFilename();
            Files.write(Paths.get(UPLOAD_FOLDER + realativeFilePath), file.getBytes());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
