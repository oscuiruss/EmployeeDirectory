package com.app.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "em_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 20, message = "Name can't consist of less than 2 and more than 20 letters")
    @Column(name = "name")
    private String name;


    @NotEmpty(message = "Patronymic can't be empty")
    @Size(min = 2, max = 20, message = "Patronymic can't consist of less than 2 and more than 20 letters")
    @Column(name = "patronymic")
    private String patronymic;


    @NotEmpty(message = "Surname can't be empty")
    @Size(min = 2, max = 20, message = "Name can't consist of less than 2 and more than 20 letters")
    @Column(name = "surname")
    private String surname;


    @Column(name = "email")
    @NotEmpty(message = "Email can't be empty")
    @Email
    private String email;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sec_id")
    private Section section;

    @Column(name = "post")
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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
        System.out.println(section.getDepartment().getName());
        return section.getDepartment();
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", department='" + section.getDepartment().getName() + '\'' +
                ", section='" + section.getName() + '\'' +
                '}';
    }
}
