package iki.novabank_user_account_service.mapper;

import iki.novabank_user_account_service.dto.RoleDto;
import iki.novabank_user_account_service.entity.Role;

public class RoleMapper {

    public static  Role mapToRole(RoleDto roleDto){
        Role role=new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }

    public static RoleDto mapToRoleDto(Role role){
        RoleDto roleDto=new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
