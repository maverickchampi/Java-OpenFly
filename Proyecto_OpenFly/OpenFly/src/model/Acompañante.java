package model;

public class Acompañante {
	private String numDocAcomp, tipDocAcomp, nomAcomp,apeAcomp;   
	private String fecNacAcomp,sexoAcomp;
	
	public Acompañante(String numDocAcomp, String tipDocAcomp, String nomAcomp, String apeAcomp, String fecNacAcomp,
			String sexoAcomp) {
		super();
		this.numDocAcomp = numDocAcomp;
		this.tipDocAcomp = tipDocAcomp;
		this.nomAcomp = nomAcomp;
		this.apeAcomp = apeAcomp;
		this.fecNacAcomp = fecNacAcomp;
		this.sexoAcomp = sexoAcomp;
	}
	
	public String getNumDocAcomp() {
		return numDocAcomp;
	}
	public String getTipDocAcomp() {
		return tipDocAcomp;
	}
	public String getNomAcomp() {
		return nomAcomp;
	}
	public String getApeAcomp() {
		return apeAcomp;
	}
	public String getFecNacAcomp() {
		return fecNacAcomp;
	}
	public String getSexoAcomp() {
		return sexoAcomp;
	}
	public void setNumDocAcomp(String numDocAcomp) {
		this.numDocAcomp = numDocAcomp;
	}
	public void setTipDocAcomp(String tipDocAcomp) {
		this.tipDocAcomp = tipDocAcomp;
	}
	public void setNomAcomp(String nomAcomp) {
		this.nomAcomp = nomAcomp;
	}
	public void setApeAcomp(String apeAcomp) {
		this.apeAcomp = apeAcomp;
	}
	public void setFecNacAcomp(String fecNacAcomp) {
		this.fecNacAcomp = fecNacAcomp;
	}
	public void setSexoAcomp(String sexoAcomp) {
		this.sexoAcomp = sexoAcomp;
	}
	
}
