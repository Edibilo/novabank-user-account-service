package iki.novabank_user_account_service.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleDto {
    private Long id;
    @NotBlank(message = "Role Name Can't be Blank")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public RoleDto(){

    }
}
