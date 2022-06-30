package com.app.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 20, message = "Name can't consist of less than 2 and more than 20 letters")
    private String name;


    @NotEmpty(message = "Patronymic can't be empty")
    @Size(min = 2, max = 20, message = "Patronymic can't consist of less than 2 and more than 20 letters")
    private String patronymic;


    @NotEmpty(message = "Surname can't be empty")
    @Size(min = 2, max = 20, message = "Name can't consist of less than 2 and more than 20 letters")
    private String surname;


    @NotEmpty(message = "Email can't be empty")
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @NotEmpty(message = "Post can't be empty")
    private String post;

    public Employee() {

    }

    public Employee(int id, String name, String patronymic, String surname, String email) {
        this.id = id;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Department getDepartment() {
        if (this.section == null) {
            return null;
        } else {
            return section.getDepartment();
        }
    }

    public String getSectionName() {
        if (Objects.nonNull(this.getSection())){
            return this.getSection().getName();
        }
        return "-";
    }

    public String getDepartmentName() {
        if (Objects.nonNull(this.getDepartment())){
            return this.getDepartment().getName();
        }
        return "-";
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
//                ", department='" + section.getDepartment().getName() + '\'' +
// у
                '}';
    }
}
