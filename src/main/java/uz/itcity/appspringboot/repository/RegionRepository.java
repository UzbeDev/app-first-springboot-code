package uz.itcity.appspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itcity.appspringboot.entity.Region;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    boolean existsRegionByNameUzEqualsIgnoreCaseAndCountryId(String name, Integer country_id);
    boolean existsRegionByNameUzEqualsIgnoreCaseAndIdNot(String name, Integer id);


    List<Region> findAllByCountryId(Integer country_id);
}
