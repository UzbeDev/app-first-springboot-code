package uz.itcity.appspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCountry {
    private String nameUz;
    private String nameRu;
    private String nameEn;
}
