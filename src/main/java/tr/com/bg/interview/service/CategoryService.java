package tr.com.bg.interview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.bg.interview.model.Category;
import tr.com.bg.interview.model.dto.CategoryDto;
import tr.com.bg.interview.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
