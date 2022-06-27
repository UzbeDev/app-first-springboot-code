package uz.itcity.appspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.itcity.appspringboot.payload.ReqRegion;
import uz.itcity.appspringboot.payload.ResRegion;
import uz.itcity.appspringboot.payload.Result;
import uz.itcity.appspringboot.serivce.RegionService;

import java.util.List;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping
    private String getRegionPage(){
        return "region";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ResRegion> getRegionList(){
        return regionService.getRegion();
    }
    @PostMapping
    @ResponseBody
    public Result addRegion(@RequestBody ReqRegion reqRegion){
        return regionService.saveRegion(reqRegion);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteRegion(@PathVariable Integer id){
        return regionService.deleteRegion(id);
    }
    @PutMapping("/{id}")
    @ResponseBody
    public Result editRegion(@PathVariable Integer id, @RequestBody ReqRegion reqRegion){
        return regionService.editRegion(id, reqRegion);
    }

    @GetMapping("/byCountry/{id}")
    @ResponseBody
    public List<ResRegion> getRegionByCountry(@PathVariable Integer id){
        return regionService.getRegionByCountry(id);
    }
}
