package com.villamorvinzie.view.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;

@Document(value = "users")
public class User {

        @Id
        private String id;

        @NotEmpty
        private String username;

        @NotEmpty
        private String email;

        @NotEmpty
        private String password;

        @NotEmpty
        private List<String> roles;

        public User(String id, @NotEmpty String username, @NotEmpty String email, @NotEmpty String password,
                        @NotEmpty List<String> roles) {
                this.id = id;
                this.username = username;
                this.email = email;
                this.password = password;
                this.roles = roles;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public List<String> getRoles() {
                return roles;
        }

        public void setRoles(List<String> roles) {
                this.roles = roles;
        }

}