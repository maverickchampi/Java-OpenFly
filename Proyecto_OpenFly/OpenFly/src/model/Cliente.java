package model;

public class Cliente {
	private String codClie;
	private String nomTipDocClie;
	private String numDocClie;
	private String nomClie;
	private String apeClie ;
	private String fecNacClie ;
	private String sexoClie;  
	private String telfClie;
	private String correoClie ;
	private String codPais, descPais;
	private String nomUsu;
	private String contUsu;

	
	public Cliente() {
		
	}

	public Cliente(String numDocClie, String nomUsu, String contUsu,String correoClie) {
		super();
		this.numDocClie = numDocClie;
		this.nomUsu = nomUsu;
		this.contUsu = contUsu;
		this.correoClie=correoClie;
	}
	
	public String getCodClie() {
		return codClie;
	}
	public String getNomTipDocClie() {
		return nomTipDocClie;
	}
	public String getNumDocClie() {
		return numDocClie;
	}
	public String getNomClie() {
		return nomClie;
	}
	public String getApeClie() {
		return apeClie;
	}
	public String getFecNacClie() {
		return fecNacClie;
	}
	public String getSexoClie() {
		return sexoClie;
	}
	public String getTelfClie() {
		return telfClie;
	}
	public String getCorreoClie() {
		return correoClie;
	}
	public String getCodPais() {
		return codPais;
	}
	public String getDescPais() {
		return descPais;
	}
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}
	public String getNomUsu() {
		return nomUsu;
	}
	public String getContUsu() {
		return contUsu;
	}
	public void setCodClie(String codClie) {
		this.codClie = codClie;
	}
	public void setNomTipDocClie(String nomTipDocClie) {
		this.nomTipDocClie = nomTipDocClie;
	}
	public void setNumDocClie(String numDocClie) {
		this.numDocClie = numDocClie;
	}
	public void setNomClie(String nomClie) {
		this.nomClie = nomClie;
	}
	public void setApeClie(String apeClie) {
		this.apeClie = apeClie;
	}
	public void setFecNacClie(String fecNacClie) {
		this.fecNacClie = fecNacClie;
	}
	public void setSexoClie(String sexoClie) {
		this.sexoClie = sexoClie;
	}
	public void setTelfClie(String telfClie) {
		this.telfClie = telfClie;
	}
	public void setCorreoClie(String correoClie) {
		this.correoClie = correoClie;
	}
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	public void setNomUsu(String nomUsu) {
		this.nomUsu = nomUsu;
	}
	public void setContUsu(String contUsu) {
		this.contUsu = contUsu;
	}
	
}
