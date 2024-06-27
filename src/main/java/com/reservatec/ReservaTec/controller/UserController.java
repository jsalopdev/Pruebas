package com.reservatec.ReservaTec.controller;
import com.reservatec.ReservaTec.model.Usuario;
import com.reservatec.ReservaTec.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/auth/check")
    public ResponseEntity<Boolean> checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean isAuthenticated = (session != null && session.getAttribute("USER") != null);
        return ResponseEntity.ok(isAuthenticated);
    }

    @GetMapping("/user/details")
    public ResponseEntity<Usuario> getUserDetails(OAuth2AuthenticationToken token) {
        Map<String, Object> attributes = token.getPrincipal().getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name"); // Asumiendo que el nombre está disponible bajo la clave "name"

        Usuario usuario = userService.getUserDetails(email, name);

        if (usuario == null) {
            return ResponseEntity.notFound().build(); // En caso de que el usuario no sea encontrado después de intentar guardarlo
        }
        return ResponseEntity.ok(usuario); // Devuelve el usuario como JSON
    }

    @PutMapping("/user/update/{codigo_tecsup}")
    public ResponseEntity<String> updateUserDetails(@PathVariable String codigo_tecsup, @RequestParam(required = false) String name, OAuth2AuthenticationToken token) {
        String email = token.getPrincipal().getAttribute("email");  // Correctly fetching email attribute
        Usuario updatedUser = userService.saveUserDetails(email, name, codigo_tecsup);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Código Tecsup actualizado con éxito para el usuario con email: " + email);
    }


    @GetMapping("/user/token")
    public String getUserToken(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return "Token de acceso: " + client.getAccessToken().getTokenValue();
    }


}