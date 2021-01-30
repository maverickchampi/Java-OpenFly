package model;

public class Vuelo {
	/*codVue,tipVuelo,cantiAsieVue,fechSalVue,horaSalVue,horaLlegVue,precioVuel,
		O.codOrig, AE.descAero,C.descCiu, D.codDest,A.descAero,CI.descCiu	*/
	private String codVue,tipVuelo,fechSalVue, horaSalVue,horaLlegVue;
    private int cantiAsieVue;
    private double precioVuel;
    
    
	//AEROPUERTO
    private String codOrig, descAeroOrig, descCiuorig;
	//CIUDAD
    private String codDest,descAeroDest,descCiuDest;
	public String getCodVue() {
		return codVue;
	}
	public void setCodVue(String codVue) {
		this.codVue = codVue;
	}
	public String getTipVuelo() {
		return tipVuelo;
	}
	public void setTipVuelo(String tipVuelo) {
		this.tipVuelo = tipVuelo;
	}
	public String getFechSalVue() {
		return fechSalVue;
	}
	public void setFechSalVue(String fechSalVue) {
		this.fechSalVue = fechSalVue;
	}
	public String getHoraSalVue() {
		return horaSalVue;
	}
	public void setHoraSalVue(String horaSalVue) {
		this.horaSalVue = horaSalVue;
	}
	public String getHoraLlegVue() {
		return horaLlegVue;
	}
	public void setHoraLlegVue(String horaLlegVue) {
		this.horaLlegVue = horaLlegVue;
	}
	public int getCantiAsieVue() {
		return cantiAsieVue;
	}
	public void setCantiAsieVue(int cantiAsieVue) {
		this.cantiAsieVue = cantiAsieVue;
	}
	public double getPrecioVuel() {
		return precioVuel;
	}
	public void setPrecioVuel(double precioVuel) {
		this.precioVuel = precioVuel;
	}
	public String getCodOrig() {
		return codOrig;
	}
	public void setCodOrig(String codOrig) {
		this.codOrig = codOrig;
	}
	public String getDescAeroOrig() {
		return descAeroOrig;
	}
	public void setDescAeroOrig(String descAeroOrig) {
		this.descAeroOrig = descAeroOrig;
	}
	public String getDescCiuorig() {
		return descCiuorig;
	}
	public void setDescCiuorig(String descCiuorig) {
		this.descCiuorig = descCiuorig;
	}
	public String getCodDest() {
		return codDest;
	}
	public void setCodDest(String codDest) {
		this.codDest = codDest;
	}
	public String getDescAeroDest() {
		return descAeroDest;
	}
	public void setDescAeroDest(String descAeroDest) {
		this.descAeroDest = descAeroDest;
	}
	public String getDescCiuDest() {
		return descCiuDest;
	}
	public void setDescCiuDest(String descCiuDest) {
		this.descCiuDest = descCiuDest;
	}
    
    
	
	
}
