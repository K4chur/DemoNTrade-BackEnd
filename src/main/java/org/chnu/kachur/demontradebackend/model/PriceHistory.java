package org.chnu.kachur.demontradebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_history", uniqueConstraints = @UniqueConstraint(columnNames = {"asset_id", "timestamp"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "open_price", precision = 12, scale = 4)
    private BigDecimal openPrice;

    @Column(name = "close_price", precision = 12, scale = 4)
    private BigDecimal closePrice;

    @Column(name = "high_price", precision = 12, scale = 4)
    private BigDecimal highPrice;

    @Column(name = "low_price", precision = 12, scale = 4)
    private BigDecimal lowPrice;

    @Column(precision = 18, scale = 4)
    private BigDecimal volume;

}
