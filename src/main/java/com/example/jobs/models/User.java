package com.example.jobs.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "u1_0")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "user_name")
    @Getter
    @Setter
    private String userName;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
}
