package main.java.model;

import java.sql.Timestamp;

public class Element {
	private int id;
	private String titre;
	private String description;
	private Timestamp dcreation;
	private Timestamp dmodification;
	private int listeid;

	// Getters & Setters

	public int getId() {
		return id;
	}

	public Timestamp getDcreation() {
		return dcreation;
	}

	public void setDcreation(Timestamp dcreation) {
		this.dcreation = dcreation;
	}

	public Timestamp getDmodification() {
		return dmodification;
	}

	public void setDmodification(Timestamp dmodification) {
		this.dmodification = dmodification;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getListeid() {
		return listeid;
	}

	public void setListeid(int listeid) {
		this.listeid = listeid;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

}
