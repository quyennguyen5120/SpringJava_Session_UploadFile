package com.example.spring.user;

import com.example.spring.modal.product;
import com.example.spring.repository.productRepository;
import com.example.spring.serivice.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class userIndex {
    @Value("${config.upload_folder}")
    String UPLOAD_FOLDER;
    @Autowired
    ProductInterface productInterface;
    @Autowired
    productRepository productRepository;
    public static Integer pageIndex = 0;
    public static Integer pageSize = 3;

    @GetMapping("/index/{pageIndex}/{pageSize}")
    public String index(Model model,@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize){
        Integer abc = null;
        if(model.getAttribute("pageSize") != null){
            abc = Integer.parseInt(model.getAttribute("pageSize").toString());
        }
        if(pageIndex != -1){
            this.pageIndex = pageIndex - 1;
        }
        if(pageSize != 0)
            this.pageSize = pageSize;
        int length = productInterface.getAll().size();
        int numberPage = Math.round (length /  this.pageSize);
        if(length % this.pageSize >= 1){
            numberPage+=1;
        }
        List<product> lstProduct = productInterface.findByPage(this.pageIndex * this.pageSize , this.pageSize * this.pageIndex + this.pageSize);

        model.addAttribute("numberPage",numberPage);
        model.addAttribute("pageIndex",this.pageIndex + 1);
        model.addAttribute("pageSize",this.pageSize);
        model.addAttribute("lstProduct",lstProduct);
        return "userView";
    }
    @GetMapping("/addsession/{idProduct}")
    public String addsession(HttpSession session,@PathVariable(value="idProduct") Integer id, Model model){
        List<product> listIdS = (List<product>) session.getAttribute("cart");
        if(listIdS ==null){
            listIdS = new ArrayList<>();
            product product = productRepository.findById(id);
            listIdS.add(product);
            session.setAttribute("cart",listIdS);
        }
        else{
//            listIdS= new ArrayList<>();
//            String[] listId = (String[]) session.getAttribute("cart");
//            for(int i =0 ; i<listId.length;i++){
//                listIdS.add(listId[i]);
//            }
//            session.setAttribute("cart",listIdS.toArray());
        }
        return "redirect:/user/index/1/3";
    }

    @GetMapping("/dohang")
    public String dohang(HttpSession session, Model model){
        if(session.getAttribute("cart") == null){
            return "redirect:/user/index";
        }
        Object listId = session.getAttribute("cart");
        List<product> lstProduct = new ArrayList<>();
//        for(int i=0;i<listId.length;i++){
//            product product = productRepository.findById(Integer.parseInt(listId[i]));
//            lstProduct.add(product);
//        }
//        model.addAttribute("lstProduct", lstProduct);
        return "cart";
    }

    @GetMapping("/redirectIndex/{pageIndex}/{pageSize}")
    public String redirectIndex(@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize){
        return "redirect:/user/index/"+pageIndex+"/"+pageSize;
    }
}
