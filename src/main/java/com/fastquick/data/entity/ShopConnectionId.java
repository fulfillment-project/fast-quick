package com.fastquick.data.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ShopConnectionId implements Serializable {

    @Column(name = "connectionId")
    private Integer connectionId;

    @Column(name = "shopId")
    private String shopId;
}
