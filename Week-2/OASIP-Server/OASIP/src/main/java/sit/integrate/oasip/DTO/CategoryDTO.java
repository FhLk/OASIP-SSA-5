package sit.integrate.oasip.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
//    @JsonIgnore
    private Integer id;
    private String categoryName;
    private String description;
    private Integer duration;
}
