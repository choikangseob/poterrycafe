package ks.poterrycafe.repository;

import ks.poterrycafe.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeleteCartItemsJPARepository extends JpaRepository<CartItems,Long> {

    Optional<CartItems>findByCartItemsId(long cartItemsId);
}
