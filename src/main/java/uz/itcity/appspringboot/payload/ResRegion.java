package uz.itcity.appspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResRegion {
    private Integer tr;
    private Integer id;
    private String name;
    private String countryName;
    private Integer countryId;
}
