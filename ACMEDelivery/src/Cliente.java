import java.util.ArrayList;

public class Cliente {

	private String email;

	private String nome;

	private String endereco;

	private ArrayList<Entrega> entregas;

	public Cliente(String email, String nome, String endereco){
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.entregas = new ArrayList<>();
	}

	public boolean adicionaEntrega(Entrega entrega) {
		int codigo = entrega.getCodigo();
		if(consultaEntrega(codigo) == null){
			entregas.add(entrega);
			return true;
		} else{
			return false;
		}
	}

	public ArrayList<Entrega> pesquisaEntregas() {
		return entregas;
	}

	public Entrega consultaEntrega(int codigo){
		for(Entrega e : entregas){
			if(e.getCodigo() == codigo){
				return e;
			}
		}
		return null;
	}

	public String getEmail(){
		return email;
	}

	public String getNome(){
		return nome;
	}

	public String getEndereco(){
		return endereco;
	}
}
