package uz.itcity.appspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itcity.appspringboot.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmailEqualsIgnoreCaseOrUsernameEqualsOrPhoneNumberEquals(String email, String username, String phoneNumber);
}
