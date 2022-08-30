package tr.com.obss.bookportal.bookportal1.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author4")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
}