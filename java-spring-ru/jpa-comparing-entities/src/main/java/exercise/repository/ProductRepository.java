package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exercise.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByTitleAndPrice(String title, int price);
}
