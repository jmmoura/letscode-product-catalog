package josiel.letscode.products.service;

import josiel.letscode.products.dto.ProductRequestDto;
import josiel.letscode.products.dto.ProductResponseDto;
import josiel.letscode.products.exception.NoSeatsAvailableException;
import josiel.letscode.products.exception.ProductNotFoundException;
import josiel.letscode.products.domain.Product;
import josiel.letscode.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAllByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public ProductResponseDto findAvailableById(String id) {
        Product product = findById(id);

        if (product.getAvailableSeats() <= 0)
            throw new NoSeatsAvailableException();

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());

        return productResponseDto;
    }

    private Product findById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new ProductNotFoundException();
    }

    public Product save(ProductRequestDto productRequestDto) {
        Product newProduct = new Product();
        newProduct.setId(UUID.randomUUID().toString());
        newProduct.setName(productRequestDto.getName());
        newProduct.setPrice(productRequestDto.getPrice());
        newProduct.setAvailableSeats(productRequestDto.getAvailableSeats());
        newProduct.setDescription(productRequestDto.getDescription());
        newProduct.setImageUrl(productRequestDto.getImageUrl());

        return productRepository.save(newProduct);
    }

    public Product update(ProductRequestDto productRequestDto, String id) {
        Product product = findById(id);

        if (productRequestDto.getName() != null && !productRequestDto.getName().isBlank())
            product.setName(productRequestDto.getName());
        if (productRequestDto.getPrice() > 0)
            product.setPrice(productRequestDto.getPrice());
        if (productRequestDto.getAvailableSeats() > 0)
            product.setAvailableSeats(productRequestDto.getAvailableSeats());
        if (productRequestDto.getDescription() != null && !productRequestDto.getDescription().isBlank())
            product.setDescription(productRequestDto.getDescription());
        if (productRequestDto.getImageUrl() != null && !productRequestDto.getImageUrl().isBlank())
            product.setImageUrl(productRequestDto.getImageUrl());

        return productRepository.save(product);
    }

    public void decrementSeats(String id) {
        Product product = findById(id);

        if (product.getAvailableSeats() > 0)
            product.setAvailableSeats(product.getAvailableSeats()-1);

        productRepository.save(product);
    }

    public void delete(String id) {
        if(!productRepository.existsById(id))
            throw new ProductNotFoundException();
        productRepository.deleteById(id);
    }
}
