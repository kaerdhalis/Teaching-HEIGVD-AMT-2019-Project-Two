package ch.heig.amt.userManager.api.endpoints;

import ch.heig.amt.userManager.api.LoginApi;
import ch.heig.amt.userManager.api.model.Credentials;
import ch.heig.amt.userManager.api.model.User;
import ch.heig.amt.userManager.business.AuthentificationService;
import ch.heig.amt.userManager.business.AuthorisationService;
import ch.heig.amt.userManager.entities.UserEntity;
import ch.heig.amt.userManager.repositories.UserRepository;
import com.auth0.jwt.exceptions.JWTCreationException;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class LoginApiController implements LoginApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorisationService authorisation;

    @Autowired
    AuthentificationService authentification;

    public ResponseEntity<String> userLogin(@ApiParam(value = "", required = true) @Valid @RequestBody Credentials credentials){

        if(userRepository.existsById(credentials.getEmail())) {

            User user = toUser(userRepository.findById(credentials.getEmail()).get());
            if(authentification.checkPassword(credentials.getPassword(), userRepository.findById(credentials.getEmail()).get().getPassword())) {
                try {
                    String token = authorisation.generateJWT(user);
                    return ResponseEntity.ok(token);
                } catch (JWTCreationException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }


    private User toUser(UserEntity entity) {

        User user = new User();
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());
        return user;
    }
}
