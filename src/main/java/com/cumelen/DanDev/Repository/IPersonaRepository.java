package com.cumelen.DanDev.Repository;

import com.cumelen.DanDev.Entity.Auto;
import com.cumelen.DanDev.Entity.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
    

            
}
