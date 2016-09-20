package dac.webscholar.shared.entities;
import dac.webscholar.shared.utils.Passwords;

import javax.persistence.*;
import java.nio.charset.Charset;

/**
 * Created by marcusviniv on 19/09/2016.
 */

@Entity
//@Access(AccessType.FIELD)
public class UserCredential {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String password;
    //private byte[] salt;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ScholarUser user;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public UserCredential(){

    }

    public UserCredential(String email, String password, UserType userType, ScholarUser user) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    @Lob
    @Column @Access(AccessType.PROPERTY)
    public byte[] getPassword(){
        //return Encryptor.encrypt(getSalt() + password);
        byte[] encPass = Passwords.hash(password, getSalt());
        return encPass;
    }*

    public void setPassword(byte[] password) {
        this.password = password;
    }
    */

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ScholarUser getUser() {
        return user;
    }

    public void setUser(ScholarUser user) {
        this.user = user;
    }

    /*
    @Lob
    @Column
    public byte[] getSalt() {
        if(salt != null && salt.length != 0){
            return salt;
        }
        else{
            salt = Passwords.getNextSalt();
            return salt;
        }
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }*/
}
