package com.reservatec.ReservaTec.service;

import com.reservatec.ReservaTec.model.Usuario;
import com.reservatec.ReservaTec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public Usuario getUserDetails(String email, String name) {
        Usuario usuario = userRepository.findByEmail(email);
        if (usuario == null) {
            // Si no existe, crea un nuevo usuario
            usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setName(name);
            userRepository.save(usuario);  // Guarda el nuevo usuario
        }
        return usuario;
    }
    public Usuario saveUserDetails(String email, String name, String codigo_tecsup) {
        Usuario usuario = userRepository.findByEmail(email);
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setEmail(email);
        }
        if (name != null && !name.isEmpty()) {
            usuario.setName(name);
        }
        usuario.setCodigoTecsup(codigo_tecsup != null ? codigo_tecsup : "");

        return userRepository.save(usuario); // Devuelve el usuario guardado
    }

}
