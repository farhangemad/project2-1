package com.learning.project2.models.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "p2_test_answers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private TestQuestion question;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "answer_text", nullable = false, length = 180)
    private String answerText;

    @Override
    public String toString() {
        return "TestAnswer{" +
                "id=" + id +
                ", isCorrect=" + isCorrect +
                ", answerText='" + answerText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAnswer that = (TestAnswer) o;
        return Objects.equals(id, that.id) && Objects.equals(isCorrect, that.isCorrect) && Objects.equals(answerText, that.answerText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isCorrect, answerText);
    }
}