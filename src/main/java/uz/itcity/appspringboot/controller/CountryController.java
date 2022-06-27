package uz.itcity.appspringboot.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.itcity.appspringboot.payload.ReqCountry;
import uz.itcity.appspringboot.payload.ResCountry;
import uz.itcity.appspringboot.payload.Result;
import uz.itcity.appspringboot.serivce.CountryService;

import java.util.List;


@Controller
@RequestMapping("/country")
public class  CountryController {

    @Autowired
    CountryService countryService;

        @GetMapping
        public String getCountry() {
            return "country";
        }

    @GetMapping("/list")
    @ResponseBody
    public List<ResCountry> getCountryList() {
        return countryService.getCountries();
    }

    @PostMapping
    @ResponseBody
    public Result addCountry(@RequestBody ReqCountry reqCountry){
        return countryService.saveCountry(reqCountry);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResCountry getOneCountry(@PathVariable Integer id){
        return countryService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Result editCountry(@PathVariable Integer id, @RequestBody ReqCountry reqCountry){
        return countryService.editCountry(id, reqCountry);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteCountry(@PathVariable Integer id){
        return countryService.deleteCountry(id);
    }
}
