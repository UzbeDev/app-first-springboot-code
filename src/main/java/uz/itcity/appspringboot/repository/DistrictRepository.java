package uz.itcity.appspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itcity.appspringboot.entity.District;
import uz.itcity.appspringboot.entity.Region;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    boolean existsDistrictByNameUzEqualsIgnoreCaseAndRegionId(String name, Integer region_id);
    boolean existsDistrictByNameUzEqualsIgnoreCaseAndIdNot(String name, Integer id);
    List<District> findAllByRegionId(Integer region_id);
}
