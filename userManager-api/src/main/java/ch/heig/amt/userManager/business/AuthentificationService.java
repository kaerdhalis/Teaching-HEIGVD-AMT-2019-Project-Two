package ch.heig.amt.userManager.business;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService  implements IAuthentificationService{

    @Override
    public String hashPassword(String plainTextPassword) {

        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String plainTextPassword, String hashedPassword) {
        try {
            return BCrypt.checkpw(plainTextPassword, hashedPassword);
        } catch (Exception e) {
            return false;
        }
    }
}
