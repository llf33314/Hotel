package com.gt.hotel.other;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeList {

    @ApiModelProperty("员工列表")
    private List<Employee> staffList;

    @ApiModelProperty("总共数量")
    private Integer count;

}
