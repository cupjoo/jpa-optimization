package jpabook.jpashop.jpaoptimization.domain.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class Album extends Item {

    private String artist;
    private String etc;

    @Builder
    public Album(String name, int price, int stockQuantity, String artist, String etc){
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }
}
