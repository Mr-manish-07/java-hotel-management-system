package org.manish07.Privacy;

import org.mindrot.jbcrypt.BCrypt;

public class Hashing_Password {

    public static String passwordEncryptor(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean passwordChecker(String password,String hashValue){
        return BCrypt.checkpw(password, hashValue);
    }
}
