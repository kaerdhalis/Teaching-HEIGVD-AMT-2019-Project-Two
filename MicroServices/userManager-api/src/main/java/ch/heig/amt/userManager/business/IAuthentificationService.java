package ch.heig.amt.userManager.business;

public interface IAuthentificationService {

    String hashPassword(String plainTextPassword);

    boolean checkPassword(String plainTextPassword, String hashedPassword);
}
