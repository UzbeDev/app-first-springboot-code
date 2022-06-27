package uz.itcity.appspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.itcity.appspringboot.entity.template.AbsNameEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"nameUz", "nameRu", "nameEn", "region_id"})
//})
public class District extends AbsNameEntity {
    @ManyToOne(optional = false)
    private Region region;

    public District( String nameUz, Region region) {
        super( nameUz);
        this.region = region;
    }
}
