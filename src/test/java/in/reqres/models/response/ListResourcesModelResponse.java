package in.reqres.models.response;
import in.reqres.models.ResourceModel;
import in.reqres.models.SupportModel;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ListResourcesModelResponse {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private ArrayList<ResourceModel> data;
    private SupportModel support;
}
