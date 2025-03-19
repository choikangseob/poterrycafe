package ks.poterrycafe.repository;

import jakarta.transaction.Transactional;
import ks.poterrycafe.entity.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshJPARepository extends JpaRepository<Refresh,Long> {

    Boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);

}
