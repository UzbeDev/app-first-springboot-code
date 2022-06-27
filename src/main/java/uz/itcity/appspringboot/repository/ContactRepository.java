package uz.itcity.appspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itcity.appspringboot.entity.Contact;
import uz.itcity.appspringboot.entity.User;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
