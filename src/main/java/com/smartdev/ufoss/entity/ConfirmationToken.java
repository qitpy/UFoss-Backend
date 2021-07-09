package com.smartdev.ufoss.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Confirmation_Token")
public class ConfirmationToken extends AbstractEntity {
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private UserEntity userEntity;

    public ConfirmationToken(String token, LocalDateTime createAt, LocalDateTime expiresAt, UserEntity userEntity) {
        this.token = token;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
        this.userEntity = userEntity;
    }
}
