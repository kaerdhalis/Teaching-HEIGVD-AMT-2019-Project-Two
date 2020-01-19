# Implementation

## User Manager API
The user manager is implemented by using swagger, it contains two endpoints,  **/login** and **/users**:
 - The login endpoint allow a user to authentificate himself by entering his credentials and is givent a token in return containing his role and allowing him differents operations.

 - the user endpoint allow an admin to create a new user or a user to reset his password.

 - The users are stored in a mysql database accessed through Spring Data. 
