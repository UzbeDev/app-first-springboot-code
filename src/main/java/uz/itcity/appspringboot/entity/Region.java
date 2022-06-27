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
//
public class Region extends AbsNameEntity {

    @ManyToOne(optional = false)
    private Country country;
}
