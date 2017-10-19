package com.gt.hotel.other;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmployeeList {
	
	@ApiModelProperty("员工列表")
	private List<Employee> staffList;
	
	@ApiModelProperty("总共数量")
	private Integer count;
	
}
