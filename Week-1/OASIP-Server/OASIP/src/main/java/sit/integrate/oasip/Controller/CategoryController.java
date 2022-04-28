package sit.integrate.oasip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sit.integrate.oasip.DTO.CategoryDTO;
import sit.integrate.oasip.Entity.Category;
import sit.integrate.oasip.Repository.CategoryRepository;
import sit.integrate.oasip.Service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository repository;

    @GetMapping("")
    public List<Category> getAllCategory(){return repository.findAll();}
}
