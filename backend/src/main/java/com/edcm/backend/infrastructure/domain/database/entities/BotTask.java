package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bot_task")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BotTask {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "commodity_id", nullable = false)
    private Commodity commodity;

    @Setter(AccessLevel.NONE)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "price")
    private Long price;

}
