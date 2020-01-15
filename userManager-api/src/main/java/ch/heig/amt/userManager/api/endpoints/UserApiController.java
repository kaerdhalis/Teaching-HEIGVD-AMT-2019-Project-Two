package ch.heig.amt.userManager.api.endpoints;

import ch.heig.amt.userManager.api.UsersApi;
import ch.heig.amt.userManager.api.model.Credentials;
import ch.heig.amt.userManager.business.AuthentificationService;
import ch.heig.amt.userManager.business.AuthorisationService;
import ch.heig.amt.userManager.entities.UserEntity;
import ch.heig.amt.userManager.api.model.User;
import ch.heig.amt.userManager.repositories.UserRepository;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class UserApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorisationService authorisation;

    @Autowired
    AuthentificationService authentification;

    public ResponseEntity<User> createAccount(@ApiParam(value = "", required = true) @Valid @RequestBody User user, @RequestHeader(name = "Authorization",required = true) String token) {

        DecodedJWT jwtDecoded = authorisation.decodeJWT(token);

        if(jwtDecoded == null)
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String email = jwtDecoded.getClaim("email").asString();
        String role = jwtDecoded.getClaim("role").asString();

        if (!role.equals("admin"))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        UserEntity newUserEntity = toUserEntity(user);
        if(userRepository.existsById(newUserEntity.getEmail())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        userRepository.save(newUserEntity);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        return entity;
    }


    public ResponseEntity<Void> modifyPassword(@ApiParam(value = "", required = true) @PathVariable("email") String email,
                                               @ApiParam(value = "", required = true) @Valid @RequestBody Credentials password,
                                               @RequestHeader(name = "Authorization",required = true) String token) {


        DecodedJWT jwtDecoded = authorisation.decodeJWT(token);

        String checkEmail = jwtDecoded.getClaim("email").asString();
        String role = jwtDecoded.getClaim("role").asString();
        if (jwtDecoded == null || !role.equals("admin") || !checkEmail.equals(email))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        UserEntity entity = userRepository.findById(email).get();
        entity.setPassword(authentification.hashPassword(password.getPassword()));
        userRepository.save(entity);

        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
