package iki.novabank_user_account_service.service;

import iki.novabank_user_account_service.dto.ResponseMessage;
import iki.novabank_user_account_service.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRile(RoleDto roleDto);
    List<RoleDto> getAllRoles();
    RoleDto getRoleById(Long roleId);
    RoleDto updateRole(RoleDto roleDto,Long roleId);
    ResponseMessage deleteRole(Long roleId);
}
