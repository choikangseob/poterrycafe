package ks.poterrycafe.repository;

import ks.poterrycafe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveProductJPARepository extends JpaRepository<Product, Long> {
}
