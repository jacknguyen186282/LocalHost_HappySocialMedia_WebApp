package com.example.HappyTweet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotEmpty
    private String text;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "postName", referencedColumnName = "postName")
//    private Post postName;
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "description", referencedColumnName = "description")
//    private Post description;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private Instant createdDate;
}
