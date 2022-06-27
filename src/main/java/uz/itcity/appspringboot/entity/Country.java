package uz.itcity.appspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.itcity.appspringboot.entity.template.AbsNameEntity;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Country extends AbsNameEntity {
}
