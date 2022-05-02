package sit.integrate.oasip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.integrate.oasip.DTO.CategoryDTO;
import sit.integrate.oasip.Service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://10.4.56.111:3000",allowedHeaders = "*",methods = {RequestMethod.GET,RequestMethod.POST})
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("")
    public List<CategoryDTO> getAllCategory(){
        return service.getCategories();
    }

    @GetMapping("/{CategoryId}")
    public CategoryDTO getCategoryById(@PathVariable Integer CategoryId){
        return service.getCategoryById(CategoryId);
    }
}
