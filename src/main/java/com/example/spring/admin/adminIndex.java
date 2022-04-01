package com.example.spring.admin;

import com.example.spring.modal.product;
import com.example.spring.serivice.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminIndex {
    @Value("${config.upload_folder}")
    String UPLOAD_FOLDER;
    @Autowired
    ProductInterface productInterface;

    @GetMapping("/index")
    public String index(Model model){
        List<product> lstProduct = productInterface.getAll();
        model.addAttribute("lstProduct",lstProduct);
        return "adminView";
    }

    @PostMapping("/createProduct")
    public String createProduct(@RequestParam("file")MultipartFile file, product p){
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
        p.setNameImage(realativeFilePath);
        productInterface.createProduct(p);
        return "redirect:/admin/index";
    }

    @GetMapping("/redrectCreateForm")
    public String redrectCreateForm(Model model){
        model.addAttribute("productt", new product());
        return "createProduct";
    }
}
