package uz.itcity.appspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.itcity.appspringboot.entity.enums.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqRegister {
    @NotBlank(message = "Ism bush bo'lmasin")
    private String firstName;

    @NotNull(message = "Familiya bush bo'lmasin")
    private String lastName;

    private String middleName;

    @NotNull(message = "Date bush bo'lmasin")
    private String birthDate ;

    @Pattern(regexp = "^[a-zA-Z]{8,32}$", message = "Username uzunligi 8tadan kam bulmasin va faqat harflardan iborat bo'lsin")
    private String username;

    @Email(message = "Siz notugri email kiridingiz")
    private String email;

    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$", message = "Tel raqam notugri")
    private String phoneNumber;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Parol 8tadan kam bulmasin ")
    private String password;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Parol 8tadan kam bulmasin ")
    private String prePassword;

    private String street;
    private String zipCode;
    private Integer districtId;
    private Gender gender;
}
