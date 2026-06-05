package com.kodnest.app.dtos;

import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class UserDTO {

    String userName;
    String password;

    public UserDTO() {

    }

    public UserDTO(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO [userName=" + userName + ", password=" + password + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, userName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        UserDTO other = (UserDTO) obj;

        return Objects.equals(password, other.password)
                && Objects.equals(userName, other.userName);
    }
}