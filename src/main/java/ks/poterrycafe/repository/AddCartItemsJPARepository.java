package ks.poterrycafe.repository;

import ks.poterrycafe.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddCartItemsJPARepository extends JpaRepository<CartItems, Long> {

}
