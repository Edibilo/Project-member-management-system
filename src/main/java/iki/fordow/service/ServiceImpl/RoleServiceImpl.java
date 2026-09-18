package iki.fordow.service.ServiceImpl;

import iki.fordow.dto.RoleDto;
import iki.fordow.entity.Role;
import iki.fordow.entity.User;
import iki.fordow.mapper.RoleMapper;
import iki.fordow.repository.RoleRepository;
import iki.fordow.repository.UserRepository;
import iki.fordow.service.RoleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {

        if(roleDto.getId()==null){
            if(roleRepository.existsByName(roleDto.getName())){
                throw new RuntimeException("Role Name Already Exist !");
            }
        }else if(roleDto.getId()!=null){
            if(roleRepository.existsByNameAndIdNot(roleDto.getName(), roleDto.getId())){
                throw new RuntimeException("Error Occurred !");
            }
        }
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
    public void deleteRole(Long roleId) {
        Role role=roleRepository.findById(roleId).orElseThrow(
                () -> new RuntimeException("Role Not Found !")
        );
        roleRepository.delete(role);
    }

    @Override
    public RoleDto findByRole(Long roleId) {
        Role role=roleRepository.findById(roleId).orElseThrow(
                () -> new RuntimeException("Role Not Found !")
        );
        return RoleMapper.mapToRoleDto(role);
    }
}
