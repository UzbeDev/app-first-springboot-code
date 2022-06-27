package uz.itcity.appspringboot.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import uz.itcity.appspringboot.entity.Contact;
import uz.itcity.appspringboot.entity.User;
import uz.itcity.appspringboot.entity.enums.RoleName;
import uz.itcity.appspringboot.payload.ReqRegister;
import uz.itcity.appspringboot.payload.Result;
import uz.itcity.appspringboot.repository.ContactRepository;
import uz.itcity.appspringboot.repository.DistrictRepository;
import uz.itcity.appspringboot.repository.RoleRepository;
import uz.itcity.appspringboot.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JavaMailSender javaMailSender;


    public Result register(ReqRegister reqRegister) throws ParseException {
        boolean equals = userRepository.existsByEmailEqualsIgnoreCaseOrUsernameEqualsOrPhoneNumberEquals(reqRegister.getEmail(), reqRegister.getUsername(), reqRegister.getPhoneNumber());
        if(!equals){
            User user=new User();
            user.setFirstName(reqRegister.getFirstName());
            user.setLastName(reqRegister.getLastName());
            user.setMiddleName(reqRegister.getMiddleName());
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
            user.setBirthDate(simpleDateFormat.parse(reqRegister.getBirthDate()));
            user.setUsername(reqRegister.getUsername());
            user.setEmail(reqRegister.getEmail());
            user.setPhoneNumber(reqRegister.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(reqRegister.getPassword()));
            Contact contact=new Contact();
            contact.setStreet(reqRegister.getStreet());
            contact.setZipCode(reqRegister.getZipCode());
            contact.setDistrict(districtRepository.findById(reqRegister.getDistrictId()).orElseThrow(() -> new ResourceAccessException("getDistrict")));
            Contact saveContact = contactRepository.save(contact);
            user.setContact(saveContact);
            user.setGender(reqRegister.getGender());
            user.setRole(Collections.singletonList(roleRepository.findByRoleName(RoleName.ROLE_USER)));
            user.setActivationCode(contact.getId().toString());
            userRepository.save(user);

            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setText("Verify check email. Click to link: "+"localhost/auth/activation");
            mailMessage.setSubject("Very new acaunt");
            javaMailSender.send(mailMessage);
            return new Result("Ro'yxatdan O'tingiz Iltimos Emailingizga qarang"+reqRegister.getEmail()+"Tasdiqlash Tugmasini Bosing",true);



        }
        return new Result("Eror",false);


    }
}
