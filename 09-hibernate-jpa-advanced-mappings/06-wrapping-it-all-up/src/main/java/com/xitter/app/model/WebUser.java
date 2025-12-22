package com.xitter.app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WebUser {
    @NotNull(message = "required")
    @Size(min=4, message = "must be at least 4 characters")
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Username may only contain letters, numbers, and underscores")
    private String username;
    private String displayName;
    @NotNull(message = "required")
    @Size(min=8, message = "must be at least 4 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!#$&]).+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character (!, #, $, &)"
    )
    private String password;

    public WebUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
