package ks.poterrycafe.repository;

import ks.poterrycafe.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateCartJPARepository extends JpaRepository<Cart, Long> {
}
