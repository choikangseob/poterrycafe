package ks.poterrycafe.repository;

import ks.poterrycafe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UpdateProductJPARepository extends JpaRepository<Product, Long> {
    Optional<Product> findByproductIdAndCreateMemberId(int productId, Long createMemberId);
}
