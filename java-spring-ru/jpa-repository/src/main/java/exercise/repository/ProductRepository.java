package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import exercise.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findAllByPriceBetweenOrderByPriceAsc(int priceFrom, int priceTo);
    // END
}
