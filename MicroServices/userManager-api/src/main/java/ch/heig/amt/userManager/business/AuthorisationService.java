package ch.heig.amt.userManager.business;

import ch.heig.amt.userManager.api.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class AuthorisationService implements IAuthorisationService{

    private final static String secret = "PwAO4wzRL1eBWJTRlTNV92tfRrKkl2";

    @Override
    public String generateJWT(User user) throws JWTCreationException {
        String token = JWT.create().withIssuer("auth-api")
                                   .withClaim("email", user.getEmail())
                                   .withClaim("role", user.getRole())
                                   .sign(Algorithm.HMAC256(secret));

        return token;
    }

    @Override
    public DecodedJWT decodeJWT(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                                      .withIssuer("auth-api").build();
            DecodedJWT jwt = verifier.verify(token);

            return jwt;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

}
