package tr.com.obss.bookportal.bookportal1.dto;

import lombok.*;
import tr.com.obss.bookportal.bookportal1.model.Book;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private int age;

}