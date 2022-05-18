package sit.integrate.oasip.exeption;

import lombok.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShowException{
    private String statusCode;
    private String error;
    private Map<String,String> errorField;
}
