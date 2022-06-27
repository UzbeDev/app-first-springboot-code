package uz.itcity.appspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itcity.appspringboot.entity.Role;
import uz.itcity.appspringboot.entity.User;
import uz.itcity.appspringboot.entity.enums.RoleName;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}
