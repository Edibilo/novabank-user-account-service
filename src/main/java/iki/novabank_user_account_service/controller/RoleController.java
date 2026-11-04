package iki.novabank_user_account_service.controller;

import iki.novabank_user_account_service.dto.ResponseMessage;
import iki.novabank_user_account_service.dto.RoleDto;
import iki.novabank_user_account_service.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto){
        RoleDto dto=roleService.createRile(roleDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> dto=roleService.getAllRoles();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("{roleId}")
    public ResponseEntity<RoleDto> getAllRole(@PathVariable Long roleId){
        RoleDto dto=roleService.getRoleById(roleId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{roleId}")
    public ResponseEntity<RoleDto> updateRole(@Valid @RequestBody RoleDto roleDto,@PathVariable Long roleId){
        RoleDto dto=roleService.updateRole(roleDto,roleId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{roleId}")
    public ResponseEntity<ResponseMessage> deleteRole(@PathVariable Long roleId){
       ResponseMessage message= roleService.deleteRole(roleId);
        return ResponseEntity.ok(message);
    }

}
