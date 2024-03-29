package com.afc.springreact.post;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.afc.springreact.file.FileAttachment;
import com.afc.springreact.user.User;

import lombok.Data;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=1000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "post", cascade = CascadeType.REMOVE)
    private FileAttachment fileAttachment;
}
