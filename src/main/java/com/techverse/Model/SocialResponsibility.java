package com.techverse.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class SocialResponsibility {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	private String title;

	private String subtitle;
	private String image;
	
	private String formatedId="";
	
	
	 public SocialResponsibility() {
		// TODO Auto-generated constructor stub
	}
	 
	 
	 

	public String getFormatedId() {
		return formatedId;
	}




	public void setFormatedId(String formatedId) {
		this.formatedId = formatedId;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public SocialResponsibility(Long id, String title, String image) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
	}




	public String getSubtitle() {
		return subtitle;
	}




	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	
	
	
	
	
}
