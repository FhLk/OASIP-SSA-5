package sit.integrate.oasip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.integrate.oasip.DTO.CategoryDTO;
import sit.integrate.oasip.Service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
//@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = "*",allowCredentials = "true")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        return ResponseEntity.ok(service.getCategories());
    }

    @GetMapping("/{CategoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer CategoryId){
        return ResponseEntity.ok(service.getCategoryById(CategoryId));
    }

    @PutMapping("/{CategoryId}")
    public ResponseEntity<CategoryDTO> Update(@PathVariable Integer CategoryId, @Valid @RequestBody CategoryDTO updateCategory){
        service.UpdateCategory(CategoryId,updateCategory);
        return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }

}

