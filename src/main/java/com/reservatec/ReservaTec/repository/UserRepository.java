package com.reservatec.ReservaTec.repository;

import com.reservatec.ReservaTec.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String correoElectronico);
}
