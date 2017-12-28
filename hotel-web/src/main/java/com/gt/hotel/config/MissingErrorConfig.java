package com.gt.hotel.config;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;

@Controller  
public class MissingErrorConfig implements ErrorController {  
	
	private static final String ERROR_PATH = "/error";  
	
//	@RequestMapping(value=ERROR_PATH)  
//	public String handleError(){  
//		return ERROR_PATH;
//	}  
	
	@Override  
	public String getErrorPath() {  
		return ERROR_PATH;
	}
	
}
