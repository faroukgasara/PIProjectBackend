package tn.esprit.spring.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Calendrier;
import tn.esprit.spring.entity.RendezVous;
import tn.esprit.spring.registration.email.EmailSender;
import tn.esprit.spring.repository.CalendrierRepository;
import tn.esprit.spring.repository.RendezVousRepsitory;

@Service
public class RendezVousService implements IRendezVousService{

	@Autowired
	RendezVousRepsitory rendezVousRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CalendrierRepository calendrRepo;
	@Autowired
	ICalendrierServices calendrService;
	@Autowired
	EmailSender sendEmail;
	@Override
	public String addRendezVous(RendezVous r ,Long userId) {
		
		byte[] array = new byte[7]; 
	    new Random().nextBytes(array);
		String code ="axx";/// new String(array, Charset.forName("UTF-8"));
		r.setCode(code); 
		User user = userRepo.findById(userId).orElse(null);
		r.setUser(user);
		if (verifRendezVousDateInput(r)==true && verifExistCalendr(r)==true) {
		rendezVousRepo.save(r);
		String link = "http://localhost:8080/WomenEmpowerment/RendezVous/confirm?code=" +code;
		sendEmail.sendrendv(r.getUser().getEmail(),buildEmailredv(r.getUser().getFirstName(),code, link) );
		return "t3addet";}
		else return "verif date input";
	}
	@Override
	public List<RendezVous> getAllRendezVous() {		
		return (List<RendezVous>) rendezVousRepo.findAll();
	}
	@Override
	public String updateRendezVous(RendezVous r,Long userId) {
		byte[] array = new byte[7]; 
	    new Random().nextBytes(array);
		String code = new String(array, Charset.forName("UTF-8"));
		r.setCode(code); 
		r.setUser(userRepo.getById(userId));
		if (verifRendezVousDateInput(r)==true) {
			rendezVousRepo.save(r);
			return "t3addet";}
			else return "verif date input";
	}
	@Override
	public String deleteRendezVous(Long id) {
		rendezVousRepo.deleteById(id);
		return "Deleted";
	}
	@Override
	public boolean verifRendezVousDateInput(RendezVous r) {
		if(r.getDebut().compareTo(r.getFin())>=0) {return false;}
		else
		return true;
	}
	@Override
	public boolean verifExistCalendrMedcin(RendezVous r) {
		List<Calendrier> listCalendr =calendrService.getAllCalendr();
		for(Calendrier c : listCalendr) {
			if( c.getUser().getAppUserRole().name()=="MEDCIN") {
				if((c.getDebut().compareTo(r.getDebut()) <=0)&& (c.getFin().after(r.getFin()) )) {
					return true;
				}
			}
		}
		
		return false;
	}
	@Override
	public boolean verifExistCalendrLawyer(RendezVous r) {
		List<Calendrier> listCalendr =calendrService.getAllCalendr();
		for(Calendrier c : listCalendr) {
			if( c.getUser().getAppUserRole().name()=="LAWYER") {
				if((c.getDebut().compareTo(r.getDebut()) <=0)&& (c.getFin().after(r.getFin()) )) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean verifExistCalendrPsy(RendezVous r) {
		List<Calendrier> listCalendr =calendrService.getAllCalendr();
		for(Calendrier c : listCalendr) {
			if( c.getUser().getAppUserRole().name()=="PSY") {
				if((c.getDebut().compareTo(r.getDebut()) <=0)&& (c.getFin().after(r.getFin()) )) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean verifExistCalendr(RendezVous r) {
		if(r.getRendezVousType().name()=="AVEC_MEDCIN") {
			return verifExistCalendrMedcin(r);
		}
		else if(r.getRendezVousType().name()=="AVEC_LAWYER") {
			return verifExistCalendrLawyer(r);
		}
		else if(r.getRendezVousType().name()=="AVEC_PSY") {
			return verifExistCalendrPsy(r);
		}
		else
		return false;
	}
	
	
	@Override
	public String buildEmailredv(String name,String code,  String link) {
		return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Meet confirmation</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Here below you need to confirm your meet " + code + ". Please click on the below link to confirm it: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Confirm Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
	}
	@Override
	public String confirm(String code) {
		rendezVousRepo.confirmRendezVous(code);
		return "Rendez-Vous confirmer ";
	}

}
