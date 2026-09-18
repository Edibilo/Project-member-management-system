package iki.fordow.service;

import iki.fordow.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);
    List<RoleDto> getAllRoles();
    void deleteRole(Long roleId);
    RoleDto findByRole(Long roleId);
}
