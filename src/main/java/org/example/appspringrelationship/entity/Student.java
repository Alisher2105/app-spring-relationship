package org.example.appspringrelationship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.hibernate.annotations.CollectionId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne//One Student to ONE Address // 1 studentga bir address
    private Address address;

    @ManyToMany
    private List<Subject> subjects;

}
