package uz.itcity.appspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.itcity.appspringboot.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact  extends AbsEntity {

    @ManyToOne(optional = false)
    private District district;

    @Column(nullable = false)
    private String street;

    private String zipCode;
}
