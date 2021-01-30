package interfaces;


import java.util.List;

import model.Acompa�ante;
import model.Cliente;

public interface PasajerosInterface {
	public List<Cliente> listaCliente();
	
	public String UltimoCliente();

	
	public Acompa�ante BuscarAcompa�ante(String CodACcomp);
	
	public void AgregarCliente(String codClie ,String nomTipDocClie ,String numDocClie , 
			String nomClie ,String apeClie,String fecNacClie ,String sexoClie ,
			String telfClie ,String correoClie ,String codPais ,String nomUsu ,String contUsu );
	
	public Cliente BuscarClienteXusu(String usu);
	
	public Cliente BuscarCliente(String codigo);
} 
