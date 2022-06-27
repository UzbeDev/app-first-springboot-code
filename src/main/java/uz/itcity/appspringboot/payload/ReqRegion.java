package uz.itcity.appspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqRegion {
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private Integer countryId;
}
