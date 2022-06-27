package uz.itcity.appspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itcity.appspringboot.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    boolean existsCountryByNameUzEqualsIgnoreCase(String name);
    boolean existsCountryByNameUzEqualsIgnoreCaseAndIdNot(String name, Integer id);
}
