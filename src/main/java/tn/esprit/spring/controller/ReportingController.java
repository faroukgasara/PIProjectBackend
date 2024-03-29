package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.service.IReportingService;

@RestController
@Api(tags = "Reporting management")
@RequestMapping("/reporting")
@CrossOrigin(origins = "http://localhost:8089")
public class ReportingController {
	@Autowired
	IReportingService reportingService;
	
	// http://localhost:8089/WomenEmpowerment/reporting/addReport/
	@PostMapping("/addReport/{reported}/{reportedby}/{reason}")
	@ResponseBody
	public void addReport(@PathVariable("reported") String reported,@PathVariable("reportedby") String reportedby,@PathVariable("reason") String reason){
		reportingService.addReport(reported, reportedby, reason);
	}
}


