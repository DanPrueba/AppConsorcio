
package com.cumelen.DanDev.Repository;

import com.cumelen.DanDev.Entity.Auto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutoRepository extends JpaRepository<Auto, Integer> {
    public Optional<Auto> findByPatente( String patente );
    public boolean existsByPatente(String patente);
}
