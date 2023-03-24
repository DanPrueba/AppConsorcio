
package com.cumelen.DanDev.Repository;

import com.cumelen.DanDev.Entity.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutoRepository extends JpaRepository<Auto, Integer> {
    
}
