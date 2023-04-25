package in.reqres.models;

import lombok.Data;

@Data
public class ResourceModel {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;
}
