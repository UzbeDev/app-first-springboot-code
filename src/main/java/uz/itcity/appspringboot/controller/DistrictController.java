package uz.itcity.appspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.itcity.appspringboot.entity.District;
import uz.itcity.appspringboot.payload.ReqDistrict;
import uz.itcity.appspringboot.payload.ResDistrict;
import uz.itcity.appspringboot.payload.Result;
import uz.itcity.appspringboot.serivce.DistrictService;

import java.util.List;

@Controller
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    DistrictService districtService;
    @GetMapping
    public String getDistrictPage(){
        return "district";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ResDistrict> getDistrict(){
        return districtService.getDistrict();
    }
    @PostMapping()
    @ResponseBody
    public Result addDistrict(@RequestBody ReqDistrict reqDistrict){
        return districtService.addDistrict(reqDistrict);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Result editDistrict(@PathVariable Integer id, @RequestBody ReqDistrict reqDistrict){
        return districtService.editDistrict(id, reqDistrict);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteDistrict(@PathVariable Integer id){
        return districtService.deleteDistrict(id);
    }

    @GetMapping("/byRegion/{id}")
    @ResponseBody
    public List<ResDistrict> getDistrictByRegion(@PathVariable Integer id){
         return districtService.getDistrictByRegion(id);
    }

}
