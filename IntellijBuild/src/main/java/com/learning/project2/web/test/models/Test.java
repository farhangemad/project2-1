package com.learning.project2.web.test.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table(name="p2_tests")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject", length = 24)
    private String subject;

    @Column(name = "test_name", length = 64)
    private String testName;

    @OneToMany
    @JoinColumn(name = "test_id")
    private List<TestQuestion> testQuestions;


    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", testName='" + testName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id.equals(test.id) && subject.equals(test.subject) && testName.equals(test.testName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, testName);
    }
}
