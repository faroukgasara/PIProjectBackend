package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Reporting;
import tn.esprit.spring.repository.ReportingRepository;

@Service
public class ReportingService implements IReportingService{
	
	@Autowired
	ReportingRepository myRepository ;
	
	@Autowired
	UserRepository userRepository ;

	@Override
	public void addReport(String reported, String reportedby,String reason) {
		User u =  userRepository.findByEmail(reported).orElse(null);
		Reporting r = new Reporting();
		r.setReason(reason);
		r.setReported_by(reportedby);
		r.setUser(u);
		Reporting r1 = myRepository.save(r);
		u.getReporting().add(r1);
	}

	@Override
	public void deleteReport(Long id) {
		myRepository.deleteById(id);
		
	}

	@Override
	public List<Reporting> getReports() {
		return myRepository.findAll();
	}



}
