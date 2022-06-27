package uz.itcity.appspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResDistrict {
    private Integer tr;
    private Integer id;
    private String name, countryName, regionName;
    private Integer regionId;
}
