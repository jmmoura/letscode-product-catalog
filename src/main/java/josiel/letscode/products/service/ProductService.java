package josiel.letscode.products.service;

import josiel.letscode.products.dto.ProductRequestDto;
import josiel.letscode.products.dto.ProductResponseDto;
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

    public ProductResponseDto findById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            Product product = optionalProduct.get();
            productResponseDto.setId(product.getId());
            productResponseDto.setName(product.getName());
            productResponseDto.setPrice(product.getPrice());
            return productResponseDto;
        }
        throw new ProductNotFoundException();
    }

    public Product save(ProductRequestDto productRequestDto) {
        Product newProduct = new Product();
        newProduct.setId(UUID.randomUUID().toString());
        newProduct.setName(productRequestDto.getName());
        newProduct.setPrice(productRequestDto.getPrice());
        newProduct.setDescription(productRequestDto.getDescription());
        newProduct.setImageUrl(productRequestDto.getImageUrl());

        return productRepository.save(newProduct);
    }

    public void delete(String id) {
        if(!productRepository.existsById(id))
            throw new ProductNotFoundException();
        productRepository.deleteById(id);
    }
}
