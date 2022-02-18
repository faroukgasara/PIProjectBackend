package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Reporting;

public interface IReportingService {
	public void addReport(String reported,String reportedby,String reason);
	public void deleteReport(Long id);
	public List<Reporting> getReports();
}
