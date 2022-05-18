package sit.integrate.oasip.exeption;

import lombok.*;

import java.util.Map;

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
