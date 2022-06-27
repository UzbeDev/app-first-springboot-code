package uz.itcity.appspringboot.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.itcity.appspringboot.payload.ReqRegister;
import uz.itcity.appspringboot.payload.ResCountry;
import uz.itcity.appspringboot.serivce.AuthService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService service;

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("reqRegister", new ReqRegister());
        return "register";
    }

    @SneakyThrows
    @PostMapping("/register")
    public String register(@Valid ReqRegister reqRegister, BindingResult bindingResult, Model model){
        model.addAttribute("reqRegister",reqRegister);
        if (bindingResult.hasErrors()){
            return "register";
        }else{
            service.register(reqRegister);
            return "login";
        }
    }
    @GetMapping("/activation")
    @ResponseBody
    public String activation(){
        return "Activation";
    }
}