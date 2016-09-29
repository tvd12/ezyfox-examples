/**
 * 
 */
package com.tvd12.ezyfox.videopoker.entities;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import com.tvd12.ezyfox.core.annotation.BuddyVariable;
import com.tvd12.ezyfox.core.annotation.GameUser;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.videopoker.model.Game;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tavandung12
 *
 */
@Data
@GameUser
@Entity
@Cacheable
@NaturalIdCache
@Table(name = "video_poker_user")
@DynamicUpdate(false)
@DynamicInsert(false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,
       region = "com.tvd12.ezyfox.videopoker.entities.VideoPokerUser")
@EqualsAndHashCode(callSuper = false, of = "dbId")
public class VideoPokerUser extends ApiGameUser {
    
    @Column(name = "last_login_ip")
    private String ip;
    
    @NaturalId(mutable = false)
    @Column(name = "name")
    private String name;
    
    @Variable(name = "1", visible = true)
    @BuddyVariable(name = "1", visible = true)
    @Column(name = "money")
    private long money = 200000; 
    
    @Variable(name ="2", visible = true)
    @BuddyVariable(name = "2", visible = true)
    @Transient
    private long gameMoney = 0;
    
    @Column(name = "last_login")
    private Date lastLoginTime = new Date();
    
    @Column(name = "last_logout")
    private Date lastLogoutTime;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int dbId;
    
    @Transient
    private Game game;
    
    public void init() {
        setName(super.getName());
        setIp(super.getIp());
    }
    
    public void increaseMoney(long offset) {
        setMoney(getMoney() + offset);
    }
    
    public void decreaseMoney(long offset) {
        setMoney(getMoney() - offset);
    }
    
    public void increaseGameMoney(long offset) {
        setGameMoney(getGameMoney() + offset);
    }
    
    public void resetGameMoney() {
        setGameMoney(0);
    }
    
    public Game newGame() {
        setGame(new Game());
        return getGame();
    }
    
    public void update(VideoPokerUser other) {
        setDbId(other.getDbId());
        setMoney(other.getMoney());
    }
    
}
