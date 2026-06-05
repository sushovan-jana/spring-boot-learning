package com.kodnest.app.controller;
import java.util.Map;

/* Here we'll see data coming in different types ->
	1. Path Variable 
	2. RequestParameters 
	3. RequestBody
*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kodnest.app.service.RequestService;

@Controller
@RequestMapping("/api")
public class RequestController {
	RequestService service;
	
//	Dependency Injection	
	public RequestController(RequestService service) {
		this.service = service;
	}
	
//	Path variables	
	@GetMapping("/show1/show11/{username}")
	@ResponseBody
	public String method1(@PathVariable("username") String name) {
		String content = service.executeService(name);
		return content;
	}
	
// Path variable
	@GetMapping("/show1/show12/{fName}/{mName}/{lName}")
	@ResponseBody
	public String updateMethod1(@PathVariable("fName") String firstName, @PathVariable("mName") String middleName, @PathVariable("lName") String lastName) {
		String content = service.executeService(firstName, middleName, lastName);
		return content;
	}
	
//	RequestParameter
	@GetMapping("/show2")
	@ResponseBody
	public String method2(@RequestParam("username") String name) { // http://localhost:8080/api/show2?username=Sushovan
		String content = service.executeService(name);
		return content;
	}
	
//	RequestBody
	@PostMapping("/show3")
	@ResponseBody
	public String method3(@RequestBody Map<String, String> user) {
		String username = user.get("username");
		String content = service.executeService(username);
		return content;
	}
}












