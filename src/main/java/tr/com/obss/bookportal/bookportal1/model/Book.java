package tr.com.obss.bookportal.bookportal1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book7")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String authorName;
    private String date;
    private String publisher;
    private String imgLink;
    private Long number;
    @ManyToOne()
    @JoinColumns(@JoinColumn(name = "author_id", referencedColumnName = "id"))
    @JsonIgnore
    private Author author;

}
