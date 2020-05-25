//package app.security;
//
//import app.model.User;
//import app.repository.interfaces.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class AuthProviderImpl implements AuthenticationProvider {
//
//    private User user;
//
//    public AuthProviderImpl(User user) {
//        this.user = user;
//    }
//
//    public AuthProviderImpl() {
//
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    @Autowired
//    private UsersRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String email = authentication.getName();
//        Optional<User> user = userRepository.findByEmail(email);
//        if (!user.isPresent()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        String password = authentication.getCredentials().toString();
//        if (!passwordEncoder.matches(password, user.get().getPassword())) {
//            throw new BadCredentialsException("Bad credentials");
//        }
//        this.user = user.get();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        return new UsernamePasswordAuthenticationToken(user, null, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return aClass.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
