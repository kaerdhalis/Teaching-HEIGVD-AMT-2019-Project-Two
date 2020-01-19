package ch.heig.amt.userManager.business;

import ch.heig.amt.userManager.api.model.User;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface IAuthorisationService {
    public String generateJWT(User user) throws JWTCreationException;

    public DecodedJWT decodeJWT(String token) ;
}
