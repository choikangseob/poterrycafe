package ks.poterrycafe.repository;

import ks.poterrycafe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeleteProductJPARepository extends JpaRepository<Product, Long> {

    Optional<Product>findByproductIdAndCreateMemberId(long id, long createMemberId);
}
