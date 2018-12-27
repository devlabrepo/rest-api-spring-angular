package pl.devlab.com.demo.services;

import org.springframework.stereotype.Service;
import pl.devlab.com.demo.model.Category;
import pl.devlab.com.demo.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

   private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
