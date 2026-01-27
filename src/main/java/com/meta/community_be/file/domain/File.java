package com.meta.community_be.file.domain;

import com.meta.community_be.common.domain.TimeStamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "file")
public class File extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String originalFileName;

    @Column()
    private String storedFileName;

    @Column()
    private String filePath;
}
