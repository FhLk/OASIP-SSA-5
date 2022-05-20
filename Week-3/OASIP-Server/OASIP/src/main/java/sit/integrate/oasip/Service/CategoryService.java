package sit.integrate.oasip.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.integrate.oasip.DTO.BookingDTO;
import sit.integrate.oasip.DTO.CategoryDTO;
import sit.integrate.oasip.Entity.EventBooking;
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

    public EventCategory UpdateCategory(Integer CategoryId, CategoryDTO updateCategory){
        EventCategory category = repository.findById(CategoryId).map(c->mapCategory(modelMapper.map(c,CategoryDTO.class),updateCategory))
                .orElseGet(()->{
                    updateCategory.setId(CategoryId);
                    return modelMapper.map(updateCategory,EventCategory.class);
                });
        updateCategory.setCategoryName(updateCategory.getCategoryName().trim());
        updateCategory.setDescription(updateCategory.getDescription().trim());
        return repository.saveAndFlush(category);
    }

    private EventCategory mapCategory(CategoryDTO oldCategory,CategoryDTO newCategory){
        oldCategory=newCategory;
        return modelMapper.map(oldCategory,EventCategory.class);
    }
}
