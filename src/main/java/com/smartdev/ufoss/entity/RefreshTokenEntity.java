package com.smartdev.ufoss.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "refreshtoken")
public class RefreshTokenEntity extends AbstractEntity{
    @OneToOne@JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    private String refreshToken;

    private LocalDateTime expiryDate;

    public RefreshTokenEntity(UserEntity user, String refreshToken, LocalDateTime expiryDate) {
        this.user = user;
        this.refreshToken = refreshToken;
        this.expiryDate = expiryDate;
    }
}
