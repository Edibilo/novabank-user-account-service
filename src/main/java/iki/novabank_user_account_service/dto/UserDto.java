package iki.novabank_user_account_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class UserDto {
    private Long id;
    @NotBlank(message = "First Name Can't be Blank")
    private String firstName;
    @NotBlank(message = "Last Name Can't be Blank")
    private String lastName;
    @NotBlank(message = "Email Can't be Blank")
    @Email(message = "Enter Valid Email")
    private String email;
    @NotBlank(message = "Password Can't be Blank")
    @Size(min = 4,message = "Size must be 4")
    private String password;
    private boolean enabled=true;
    private Long roleId;
    private LocalDateTime createdAt;

    public UserDto(Long id, String firstName, String lastName, String email, String password,
                   boolean enabled, Long roleId, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roleId = roleId;
        this.createdAt = createdAt;
    }

    public UserDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "First Name Can't be Blank") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First Name Can't be Blank") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last Name Can't be Blank") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last Name Can't be Blank") String lastName) {
        this.lastName = lastName;
    }

    public @Email(message = "Enter Valid Email") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Enter Valid Email") String email) {
        this.email = email;
    }

    public @Size(min = 4, message = "Size must be 4") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 4, message = "Size must be 4") String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
