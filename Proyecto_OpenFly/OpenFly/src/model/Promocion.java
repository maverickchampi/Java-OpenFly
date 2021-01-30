package model;

public class Promocion {
	
	private String cod_Promo,descrip_Promo;
	private double porcentaje_desc;
	private String  fechaIni_Promo,fechaFin_Promo;
	
	/*-------------------------------*/
	public String getCod_Promo() {
		return cod_Promo;
	}
	public void setCod_Promo(String cod_Promo) {
		this.cod_Promo = cod_Promo;
	}
	public String getDescrip_Promo() {
		return descrip_Promo;
	}
	public void setDescrip_Promo(String descrip_Promo) {
		this.descrip_Promo = descrip_Promo;
	}
	public double getPorcentaje_desc() {
		return porcentaje_desc;
	}
	public void setPorcentaje_desc(double porcentaje_desc) {
		this.porcentaje_desc = porcentaje_desc;
	}
	public String getFechaIni_Promo() {
		return fechaIni_Promo;
	}
	public void setFechaIni_Promo(String fechaIni_Promo) {
		this.fechaIni_Promo = fechaIni_Promo;
	}
	public String getFechaFin_Promo() {
		return fechaFin_Promo;
	}
	public void setFechaFin_Promo(String fechaFin_Promo) {
		this.fechaFin_Promo = fechaFin_Promo;
	}
	
	
}
