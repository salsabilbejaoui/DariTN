package tn.esprit.spring.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Achat;
import tn.esprit.spring.entity.Action;
import tn.esprit.spring.entity.AdState;
import tn.esprit.spring.entity.Annonce;
import tn.esprit.spring.entity.AnnonceReview;
import tn.esprit.spring.entity.ContractType;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.Payementmode;
import tn.esprit.spring.entity.State;
import tn.esprit.spring.entity.StatePrice;
import tn.esprit.spring.entity.Type;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.AnnonceRepository;
import tn.esprit.spring.service.AchatService;
import tn.esprit.spring.service.AchatServiceImpl;
import tn.esprit.spring.service.AnnReviewService;
import tn.esprit.spring.service.AnnService;
import tn.esprit.spring.service.EmailService;
import tn.esprit.spring.service.NotificationServiceImpl;
import tn.esprit.spring.service.UserSerivce;

@Scope(value = "session")
@Controller(value = "achatController")
@ELBeanName(value = "achatController")

public class AchatConroller {

	@Autowired
	AchatService iachatservice;

	private Achat achat;

	private User authenticatedUser;

	private Date datedachat;

	private String history;

	private Payementmode Payementmode;

	private String Remarque;

	private String RecomendationLetter;

	private List<Achat> achats;

	public Achat getAchat() {
		return achat;
	}

	public void setAchat(Achat achat) {
		this.achat = achat;
	}

	// public List<Achat> getAchats() {
	// return achats;
	// }

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	public Date getDatedachat() {
		return datedachat;
	}

	public void setDatedachat(Date datedachat) {
		this.datedachat = datedachat;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public Payementmode getPayementmode() {
		return Payementmode;
	}

	public void setPayementmode(Payementmode payementmode) {
		Payementmode = payementmode;
	}

	public String getRemarque() {
		return Remarque;
	}

	public void setRemarque(String remarque) {
		Remarque = remarque;
	}

	public String getRecomendationLetter() {
		return RecomendationLetter;
	}

	public void setRecomendationLetter(String recomendationLetter) {
		RecomendationLetter = recomendationLetter;
	}

	@Override
	public String toString() {
		return "AchatConroller [datedachat=" + datedachat + ", history=" + history + ", Payementmode=" + Payementmode
				+ ", Remarque=" + Remarque + ", RecomendationLetter=" + RecomendationLetter + "]";
	}

	/**
	 * 
	 */
	public AchatConroller() {
		super();
	}

	/**
	 * @param datedachat
	 * @param history
	 * @param payementmode
	 * @param remarque
	 * @param recomendationLetter
	 */
	public AchatConroller(Date datedachat, String history, tn.esprit.spring.entity.Payementmode payementmode,
			String remarque, String recomendationLetter) {
		super();
		this.datedachat = datedachat;
		this.history = history;
		Payementmode = payementmode;
		Remarque = remarque;
		RecomendationLetter = recomendationLetter;
	}

	public long ajouterAchat(Achat achat) {
		iachatservice.ajouterAchat(achat);
		return achat.getId();
	}

	public void addAchat() {
		iachatservice.addOrUpdateAchat(new Achat(datedachat, history, Payementmode, Remarque, RecomendationLetter));
	}

	public void updateAchat() {
		iachatservice.addOrUpdateAchat(new Achat(datedachat, history, Payementmode, Remarque, RecomendationLetter));
	}

	public List<Achat> getAllAchats() {
		achats = iachatservice.retrieveAllAchats();
		return achats;
	}

	public Payementmode[] getPayementmodes() {
		return Payementmode.values();
	}

	public String dogoto() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/user/mesachatsprevu.xhtml?faces-redirect=true";
	}

	public List<Achat> getAchats() {
		achats = iachatservice.getAllAchats();
		return achats;
	}

	public void displayAchat(Achat empl) {
		this.setDatedachat(empl.getDatedachat());
		this.setPayementmode(empl.getPayementmode());
		this.setRemarque(empl.getRemarque());
		this.setRecomendationLetter(empl.getRecomendationLetter());
		this.setHistory(empl.getHistory());
		

	}

	public void removeAchat(String id) {
		iachatservice.deleteAchat(id);
	}

	private Integer achatIdToBeUpdated;

	public Integer getAchatIdToBeUpdated() {
		return achatIdToBeUpdated;
	}

	public void setAchatIdToBeUpdated(Integer achatIdToBeUpdated) {
		this.achatIdToBeUpdated = achatIdToBeUpdated;
	}

	
}
