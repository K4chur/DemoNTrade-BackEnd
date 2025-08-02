package org.chnu.kachur.demontradebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chnu.kachur.demontradebackend.util.LLMMessagesUtil.LLMMessageSender;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "llm_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LLMMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    @JsonBackReference
    private LLMChatSession llmChatSession;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private LLMMessageSender sender;

    @Column(nullable = false)
    private String message;

    @Column(name = "created_at")
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
}
