package ks.poterrycafe.repository;

import ks.poterrycafe.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeleteCartJPARepository extends JpaRepository<Cart, Long> {

    Optional<Cart>findByCartIdAndCreateMemberId(long id, long createMemberId);
}
