package uz.itcity.appspringboot.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itcity.appspringboot.entity.District;
import uz.itcity.appspringboot.entity.Region;
import uz.itcity.appspringboot.payload.ReqDistrict;
import uz.itcity.appspringboot.payload.ResDistrict;
import uz.itcity.appspringboot.payload.Result;
import uz.itcity.appspringboot.repository.DistrictRepository;
import uz.itcity.appspringboot.repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    RegionRepository regionRepository;

    public List<ResDistrict> getDistrict() {
        List<District> districtList = districtRepository.findAll();
        List<ResDistrict> resDistrictList = new ArrayList<>();
        for (int i = 0; i < districtList.size(); i++) {
            resDistrictList.add(new ResDistrict(i + 1, districtList.get(i).getId(), districtList.get(i).getNameUz(), districtList.get(i).getRegion().getCountry().getNameUz(), districtList.get(i).getRegion().getNameUz(), districtList.get(i).getRegion().getId()));
        }
        return resDistrictList;
    }

    public Result addDistrict(ReqDistrict reqDistrict) {
        boolean existBy = districtRepository.existsDistrictByNameUzEqualsIgnoreCaseAndRegionId(reqDistrict.getNameUz(), reqDistrict.getRegionId());
        if (!existBy) {
            Optional<Region> optionalRegion = regionRepository.findById(reqDistrict.getRegionId());
            if (optionalRegion.isPresent()) {
                Region region = optionalRegion.get();
                District district = new District(reqDistrict.getNameUz(), region);
                districtRepository.save(district);
                return new Result("Saved District", true);
            }
            return new Result("Region not found", false);
        }
        return new Result("District already exist ", false);
    }

    public Result editDistrict(Integer id, ReqDistrict reqDistrict) {
        boolean existsById = districtRepository.existsById(id);
        if (existsById) {
            boolean existBy = districtRepository.existsDistrictByNameUzEqualsIgnoreCaseAndIdNot(reqDistrict.getNameUz(), id);
            if (!existBy) {
                Region region = regionRepository.findById(reqDistrict.getRegionId()).get();
                District district = districtRepository.findById(id).get();
                district.setNameUz(reqDistrict.getNameUz());
                district.setRegion(region);
                districtRepository.save(district);
                return new Result("Edited", true);
            }
            return new Result("district already exist", false);
        }
        return new Result("district already exist", false);
    }

    public Result deleteDistrict(Integer id) {
        districtRepository.deleteById(id);
        return new Result("Delete district", true);

    }

    public List<ResDistrict> getDistrictByRegion(Integer id) {
        List<District> allByRegionId = districtRepository.findAllByRegionId(id);
        List<District> districtList = districtRepository.findAll();
        List<ResDistrict> resDistrictList = new ArrayList<>();
        for (int i = 0; i < districtList.size(); i++) {
            resDistrictList.add(new ResDistrict(i + 1, districtList.get(i).getId(), districtList.get(i).getNameUz(), districtList.get(i).getRegion().getCountry().getNameUz(), districtList.get(i).getRegion().getNameUz(), districtList.get(i).getRegion().getId()));
        }
        return resDistrictList;
    }
}

