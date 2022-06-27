package uz.itcity.appspringboot.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itcity.appspringboot.entity.Country;
import uz.itcity.appspringboot.payload.ReqCountry;
import uz.itcity.appspringboot.payload.ResCountry;
import uz.itcity.appspringboot.payload.Result;
import uz.itcity.appspringboot.repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class    CountryService {
    @Autowired
    CountryRepository countryRepository;

    public List<ResCountry> getCountries(){
        List<Country> countryList = countryRepository.findAll();
        List<ResCountry> resCountryList=new ArrayList<>();
        for (int i = 0; i <countryList.size() ; i++) {
            ResCountry resCountry=new ResCountry();
            resCountry.setTr(i+1);
            resCountry.setId(countryList.get(i).getId());
            resCountry.setName(countryList.get(i).getNameUz());
            resCountryList.add(resCountry);
        }
        return resCountryList;
    }

    public Result saveCountry(ReqCountry reqCountry){
        if (!countryRepository.existsCountryByNameUzEqualsIgnoreCase(reqCountry.getNameUz())){
            Country country=new Country();
            country.setNameUz(reqCountry.getNameUz());
            countryRepository.save(country);
            return new Result("Successfully saved country", true);
        }else{
            return new Result("Country already exists", false);
        }
    }

    public ResCountry getOne(Integer id){
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()){
            Country country = optionalCountry.get();
            return new ResCountry(country.getId(),country.getId(), country.getNameUz());
        }else{
            return new ResCountry();
        }

    }

    public Result editCountry(Integer id, ReqCountry reqCountry){
        boolean existsCountry = countryRepository.existsCountryByNameUzEqualsIgnoreCaseAndIdNot(reqCountry.getNameUz(), id);
        if(!existsCountry){
            Optional<Country> optionalCountry = countryRepository.findById(id);
            if (optionalCountry.isPresent()){
                Country country = optionalCountry.get();
                country.setNameUz(reqCountry.getNameUz());
                countryRepository.save(country);
                return new Result("Updated country", true);
            }else{
                return new Result("County not found",false);
            }
        }else {
            return new Result("Country already exists", false);
        }
    }

    public Result deleteCountry(Integer id){
        Optional<Country> byId = countryRepository.findById(id);
        if (byId.isPresent()){
            countryRepository.deleteById(id);
            return new Result("Successfully deleted country", true);
        }
        return new Result("Country not found",false);
    }

}
