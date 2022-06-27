package uz.itcity.appspringboot.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itcity.appspringboot.entity.Country;
import uz.itcity.appspringboot.entity.Region;
import uz.itcity.appspringboot.payload.ReqRegion;
import uz.itcity.appspringboot.payload.ResRegion;
import uz.itcity.appspringboot.payload.Result;
import uz.itcity.appspringboot.repository.CountryRepository;
import uz.itcity.appspringboot.repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CountryRepository countryRepository;

    public List<ResRegion> getRegion() {
        List<ResRegion> resRegionList = new ArrayList<>();
        List<Region> regions = regionRepository.findAll();
        for (int i = 0; i < regions.size(); i++) {
            ResRegion resRegion = new ResRegion(
                    i + 1,
                    regions.get(i).getId(),
                    regions.get(i).getNameUz(),
                    regions.get(i).getCountry().getNameUz(),
                    regions.get(i).getCountry().getId()
            );
            resRegionList.add(resRegion);
        }
        return resRegionList;
    }

    public Result saveRegion(ReqRegion reqRegion) {
        boolean existRegion = regionRepository.existsRegionByNameUzEqualsIgnoreCaseAndCountryId(reqRegion.getNameUz(), reqRegion.getCountryId());
        if (!existRegion) {
            Optional<Country> optionalCountry = countryRepository.findById(reqRegion.getCountryId());
            if (optionalCountry.isPresent()) {
                Country country = optionalCountry.get();
                Region region = new Region();
                region.setNameUz(reqRegion.getNameUz());
                region.setCountry(country);
                regionRepository.save(region);
                return new Result("successfully save region", true);
            } else {
                return new Result("Bunday Country yoq", false);
            }
        } else {
            return new Result("Bunday Country bor", false);
        }
    }

    public Result deleteRegion(Integer id) {
        Optional<Region> byId = regionRepository.findById(id);
        if (byId.isPresent()) {
            regionRepository.deleteById(id);
            return new Result("Deleted Region", true);
        } else {
            return new Result("Region not found", false);
        }
    }

    public Result editRegion(Integer id, ReqRegion reqRegion) {
        Optional<Region> byId = regionRepository.findById(id);
        if (byId.isPresent()) {
            boolean existRegion = regionRepository.existsRegionByNameUzEqualsIgnoreCaseAndIdNot(reqRegion.getNameUz(), id);
            if (!existRegion) {
                Optional<Country> byId1 = countryRepository.findById(reqRegion.getCountryId());
                if (byId1.isPresent()) {
                    Country country = byId1.get();
                    Region region = byId.get();
                    region.setNameUz(reqRegion.getNameUz());
                    region.setCountry(country);
                    regionRepository.save(region);
                    return new Result("Region Successfully Edit", true);
                } else {
                    return new Result("Bunday Country yoq", false);
                }
            } else {
                return new Result("Bunday Region bor", false);
            }
        } else {
            return new Result("bunday region yoq", false);
        }
    }

    public List<ResRegion> getRegionByCountry(Integer id) {
        List<Region> regionList = regionRepository.findAllByCountryId(id);
        List<ResRegion> resRegionList = new ArrayList<>();
        for (int i = 0; i < regionList.size(); i++) {
            ResRegion resRegion = new ResRegion(
                    i + 1,
                    regionList.get(i).getId(),
                    regionList.get(i).getNameUz(),
                    regionList.get(i).getCountry().getNameUz(),
                    regionList.get(i).getCountry().getId()
            );
            resRegionList.add(resRegion);
        }
        return resRegionList;
    }
}

