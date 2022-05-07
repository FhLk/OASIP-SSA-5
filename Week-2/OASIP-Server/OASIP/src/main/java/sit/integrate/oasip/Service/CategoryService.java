package sit.integrate.oasip.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.integrate.oasip.DTO.CategoryDTO;
import sit.integrate.oasip.Entity.EventCategory;
import sit.integrate.oasip.Repository.CategoryRepository;
import sit.integrate.oasip.Utils.ListMapper;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    public List<CategoryDTO> getCategories(){
        List<EventCategory> categoryList = repository.findAll();
        return listMapper.mapList(categoryList, CategoryDTO.class, modelMapper);
    }

    public CategoryDTO getCategoryById(Integer categoryId){
        EventCategory category =repository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Category id"+ categoryId +" Does Not Exist !!!"
                ));
        return modelMapper.map(category,CategoryDTO.class);
    }
}
