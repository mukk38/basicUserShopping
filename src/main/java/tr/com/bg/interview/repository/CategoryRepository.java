package tr.com.bg.interview.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bg.interview.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}