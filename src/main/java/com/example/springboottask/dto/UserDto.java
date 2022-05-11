package com.example.springboottask.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
    private Long id;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[\\w\\-\\s]*$",
            message = "Allowed symbols: a-z, A-Z, 0-9, _ - ")
    @Size(min = 2, max = 45, message = "Name max length is 45 symbols")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[\\w\\-\\s]*$",
            message = "Allowed symbols: a-z, A-Z, 0-9, _ - ")
    @Size(min = 2, max = 45, message = "Name max length is 45 symbols")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email
    @Size(max = 45, message = "Name max length is 45 symbols")
    private String email;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[\\w\\-\\s]*$",
            message = "Allowed symbols: a-z, A-Z, 0-9, _ - ")
    @Size(min = 2, max = 45, message = "Name max length is 45 symbols")
    private String city;

    private List<OrderDto> orders = new ArrayList<>();

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", orders=" + orders +
                '}';
    }
}
