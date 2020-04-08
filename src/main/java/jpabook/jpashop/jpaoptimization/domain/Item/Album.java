package jpabook.jpashop.jpaoptimization.domain.Item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Album extends Item {

    private String artist;
    private String etc;

    @Builder
    public Album(String name, String artist, String etc){
        super(name);
        this.artist = artist;
        this.etc = etc;
    }
}
