import java.util.ArrayList;

public class CadastroEntregas{

	private ArrayList<Entrega> entregas;

	public CadastroEntregas(){
		this.entregas = new ArrayList<>();
	}

	public boolean cadastraEntrega(Entrega entrega) {
		int codigo = entrega.getCodigo();
		if(pesquisaEntrega(codigo) != null){
			return false;
		} else {
			return entregas.add(entrega);
		}
	}

	public Entrega pesquisaEntrega(int codigo) {
		for(Entrega e : entregas){
			if(e.getCodigo() == codigo){
				return e;
			}
		}
		return null;
	}

	public ArrayList<Entrega> pesquisaEntrega(String email){
		ArrayList<Entrega> entregasDoCliente = new ArrayList<>();
		for(Entrega e : entregas){
			if(e.getCliente().getEmail().equals(email)){
				entregasDoCliente.add(e);
			}
		}

		if(!entregas.isEmpty()){
			return entregasDoCliente;
		} else { 
			return null;
		}
	}

	public ArrayList<Entrega> pesquisaEntregas(){
		return entregas;
	}
}
