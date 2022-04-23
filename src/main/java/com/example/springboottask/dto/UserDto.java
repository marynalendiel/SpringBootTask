package com.example.springboottask.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

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
