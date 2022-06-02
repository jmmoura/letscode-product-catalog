package josiel.letscode.products.component;

import josiel.letscode.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

    private final ProductService productService;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "groupId")
    void listener(String productId) {
        productService.decrementSeats(productId);
    }

}
