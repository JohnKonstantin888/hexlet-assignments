package exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductRepository productRepository;

    // BEGIN
    @GetMapping
    public List<Product> findProductsByMinMaxPrice(@RequestParam(required = false) Integer min,
                                                   @RequestParam(required = false) Integer max) {
        int minPrice = min != null && min > 0 ? min : 0;
        int maxPrice = max != null ? max : Integer.MAX_VALUE;
        return productRepository.findAllByPriceBetweenOrderByPriceAsc(minPrice, maxPrice);
    }
    // END

    @GetMapping("/{id}")
    public Product findById(@PathVariable long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
