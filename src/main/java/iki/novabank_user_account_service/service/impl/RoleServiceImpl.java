package iki.novabank_user_account_service.service.impl;

import iki.novabank_user_account_service.dto.ResponseMessage;
import iki.novabank_user_account_service.dto.RoleDto;
import iki.novabank_user_account_service.entity.Role;
import iki.novabank_user_account_service.exception.NotFoundException;
import iki.novabank_user_account_service.mapper.RoleMapper;
import iki.novabank_user_account_service.repository.RoleRepository;
import iki.novabank_user_account_service.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto createRile(RoleDto roleDto) {
        Role role= RoleMapper.mapToRole(roleDto);
        Role savedRole=roleRepository.save(role);
        return RoleMapper.mapToRoleDto(savedRole);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles=roleRepository.findAll();
        return roles.stream().map(RoleMapper::mapToRoleDto).collect(Collectors.toList());
    }

    @Override
    public RoleDto getRoleById(Long roleId) {
        Role role=roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role Not Found !")
        );
        return RoleMapper.mapToRoleDto(role);
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Long roleId) {
        Role role=roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role Not Found !")
        );
        role.setName(roleDto.getName());
        Role updateRole=roleRepository.save(role);
        return RoleMapper.mapToRoleDto(updateRole);
    }

    @Override
    public ResponseMessage deleteRole(Long roleId) {
        Role role=roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role Not Found !")
        );
        roleRepository.delete(role);
        ResponseMessage message=new ResponseMessage();
        message.setMessage("Role Deleted Successfully !");
        return message;
    }
}
