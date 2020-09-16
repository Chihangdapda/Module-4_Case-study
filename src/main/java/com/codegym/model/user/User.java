package com.codegym.model.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false)
    @NotEmpty
    @NotBlank
    private String userName;

    @Column(nullable = false)
    @NotEmpty
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @Column(nullable = false)
    @NotEmpty
    @NotBlank
    private String firstName;

    @Column(nullable = false)
    @NotEmpty
    @NotBlank
    private String lastName;

    @Min(18)
    @Column(nullable = false)
    private int age;

    @Column(nullable = false, unique = false)
    @NotEmpty
    @NotBlank
    @Email(message = "{errors.invalid_email}")
    private String email;

    @Column(nullable = false)
    @NotEmpty
    @NotBlank
    private String phoneNumber;

    @Column(nullable = false)
    @NotEmpty
    @NotBlank
    private String address;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(Long userId, @NotEmpty @NotBlank String userName, @NotEmpty @NotBlank @Size(min = 5, max = 20) String password, @NotEmpty @NotBlank String firstName, @NotEmpty @NotBlank String lastName, @Min(18) @NotEmpty @NotBlank int age, @NotEmpty @NotBlank @Email(message = "{errors.invalid_email}") String email, @NotEmpty @NotBlank String phoneNumber, @NotEmpty @NotBlank String address, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        String number = user.getPhoneNumber();
        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "number.empty");
        if (number.length() > 11 || number.length() < 10) {
            errors.rejectValue("phoneNumber", "number.length");
        }
        if (!number.startsWith("0")) {
            errors.rejectValue("phoneNumber", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("phoneNumber", "number.matches");
        }
    }
}

