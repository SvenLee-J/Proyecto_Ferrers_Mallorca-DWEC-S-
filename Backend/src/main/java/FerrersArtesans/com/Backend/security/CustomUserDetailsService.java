package FerrersArtesans.com.Backend.security;

import FerrersArtesans.com.Backend.model.User;
import FerrersArtesans.com.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Registra como servicio de Spring

// "implemensts ..." es necesario para que sea detectado por Spring Security.
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired // Inyecata dependencias automaticamente.
    private UserRepository userRepository;

    @Override // Indica que este metodo puede sobreescribir un metodo de la clase padre.
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email) // Busca usaurio por emial en BD. 
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Devuelve UserDetails y transforma el User en Objeto que entiende Spring Security.
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                
                // se usa "ROLE_" para evitar un fallo en el tipo de dato que recibe getrole
                .authorities("ROLE_" + user.getRol()) 
                .build();
    }
}
