package pl.devlab.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.devlab.com.demo.model.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long> {


}
