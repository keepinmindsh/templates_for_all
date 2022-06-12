package bong.lines.domain.jpa.model.dto;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private int age;

    private UserDto() {
    }

    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
