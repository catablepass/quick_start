package ru.diasoft.demo.domain;

import javax.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "greeting")
@NoArgsConstructor
@Getter
@Setter
public class Greeting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "content")
    private String content;

    public Greeting(String content) {
        this.content = content;
    }
}
