import java.util.ArrayList;

public class Clientela {

	private ArrayList<Cliente> clientes;

	public Clientela(){
		this.clientes = new ArrayList<>();
	}

	public boolean cadastraCliente(Cliente cliente) {
		String email = cliente.getEmail();
		if(pesquisaCliente(email) != null){
			return false;
		} else {
			return clientes.add(cliente);
		}
	}

	public Cliente pesquisaCliente(String email) {
		for(Cliente c : clientes){
			if(c.getEmail().equals(email)){
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Cliente> pesquisaClientes(){
		return clientes;
	}

}
