import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class ACMEDelivery {

	private CadastroEntregas cadastroEntregas;

	private Clientela clientela;

	private Scanner entrada = null;

	private PrintStream saidaPadrao = System.out;

	public ACMEDelivery(){
		try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("arquivoentrada.txt"));
            entrada = new Scanner(streamEntrada); 
            PrintStream streamSaida = new PrintStream(new File("arquivosaida.txt"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);

		clientela = new Clientela();
		cadastroEntregas = new CadastroEntregas();
	}

	public void executa() {
		cadastraClientes();
		cadastraEntregas();
		clientesCadastrados();
		entregasCadastradas();
		dadosCliente();
		dadosEntrega();
        entregasCliente();
		maiorValor();
		enderecoEntrega();
		somatorioValores();
	}

	public void cadastraClientes(){
		String email;
		String nome;
		String endereco;

		email = entrada.nextLine();

		while(!email.equals("-1")){
			nome = entrada.nextLine();
			endereco = entrada.nextLine();

			Cliente cliente = new Cliente(email, nome, endereco);

			if(clientela.cadastraCliente(cliente)){
				System.out.print("1;");
				System.out.print(email + ";");
				System.out.print(nome + ";");
				System.out.println(endereco);
			}
			
			email = entrada.nextLine();
		}
	}

	public void cadastraEntregas(){
		int codigo;
		double valor;
		String descricao;
		String email;
		Cliente cliente;

		codigo = entrada.nextInt();

		while(codigo != -1){
			valor = entrada.nextDouble();
			entrada.nextLine();
			descricao = entrada.nextLine();
			email = entrada.nextLine();
			cliente = clientela.pesquisaCliente(email);		
			
			if(cliente != null){
				Entrega entrega = new Entrega(codigo, valor, descricao, cliente);	
				
				if(cadastroEntregas.cadastraEntrega(entrega)){
					System.out.print("2;");
					System.out.print(codigo + ";");
					System.out.print(valor + ";");
					System.out.print(descricao + ";");
					System.out.println(email);
				}

				codigo = entrada.nextInt();
				
			} else { 
				codigo = entrada.nextInt();
			}
		}
	}

	public void clientesCadastrados(){
		ArrayList<Cliente> clientes = clientela.pesquisaClientes();
		System.out.println("3;" + clientes.size());
	}

	public void entregasCadastradas(){
		ArrayList<Entrega> entregas = cadastroEntregas.pesquisaEntregas();
		System.out.println("4;" + entregas.size());
	}

	public void dadosCliente(){
		entrada.nextLine();
		String email = entrada.nextLine();
		Cliente cliente = clientela.pesquisaCliente(email);

		if(cliente != null){
			System.out.print("5;");
			System.out.print(cliente.getEmail() + ";");
			System.out.print(cliente.getNome() + ";");
			System.out.println(cliente.getEndereco());
		} else {
			System.out.println("5;Cliente Inexistente");
		}
	}

	public void dadosEntrega(){
		int codigo = entrada.nextInt();
		Entrega entrega = cadastroEntregas.pesquisaEntrega(codigo);

		if(entrega != null){
			System.out.print("6;");
			System.out.print(entrega.getCodigo() + ";");
			System.out.print(entrega.getValor() + ";");
			System.out.print(entrega.getDescricao() + ";");
			System.out.print(entrega.getCliente().getEmail() + ";");
			System.out.print(entrega.getCliente().getNome() +";");
			System.out.println(entrega.getCliente().getEndereco());
		} else {
			System.out.println("6;Entrega Inexistente");
		}
	}

	public void entregasCliente(){
		String email = entrada.next();
		Cliente cliente = clientela.pesquisaCliente(email);

		if(cliente != null){
			ArrayList<Entrega> entregas = cadastroEntregas.pesquisaEntrega(email);

			if(entregas != null){
				
				for(Entrega e : entregas){
					System.out.print("7;");
					System.out.print(email + ";");
					System.out.print(e.getCodigo() + ";");
					System.out.print(e.getValor() + ";");
					System.out.println(e.getDescricao());
				}
			}
		} else {
			System.out.println("7;Cliente Inexistente");	
		}
	}

	public void maiorValor(){
		ArrayList<Entrega> entregas = cadastroEntregas.pesquisaEntregas();

		if(entregas != null){
			Entrega entrega = new Entrega(0, 0, null, null);
			double maiorValor = 0;

			for(Entrega e : entregas){

				if(e.getValor() > maiorValor){
					maiorValor = e.getValor();
					entrega = e;
				}
			}
			System.out.print("8;");
			System.out.print(entrega.getCodigo() + ";");
			System.out.print(entrega.getValor()  + ";");
			System.out.println(entrega.getDescricao());
		} else {
			System.out.println("8;Entrega Inexistente");
		}
	}

	public void enderecoEntrega(){
		int codigo = entrada.nextInt();
		entrada.nextLine();
		Entrega entrega = cadastroEntregas.pesquisaEntrega(codigo);

		if(entrega != null){
			System.out.print("9;");
			System.out.print(codigo + ";");
			System.out.print(entrega.getValor() + ";");
			System.out.print(entrega.getDescricao() + ";");
			System.out.println(entrega.getCliente().getEndereco());
		} else {
			System.out.println("9;Entrega Inexistente");
		}
	}

	public void somatorioValores(){
		String email = entrada.nextLine();
		Cliente cliente = clientela.pesquisaCliente(email);

		if(cliente != null){
			ArrayList<Entrega> entregas = cadastroEntregas.pesquisaEntrega(email);

			if(entregas != null && !entregas.isEmpty()){
				double valor = 0;
				for(Entrega e : entregas){
					valor += e.getValor();
				}
				String valorF = String.format(Locale.ENGLISH, "%.2f", valor);

				System.out.print("10;");
				System.out.print(email + ";");
				System.out.print(cliente.getNome() + ";");
				System.out.println(valorF);
			} else {
			System.out.println("10;Entrega Inexistente");
			} 
		} else {
			System.out.println("10;Cliente Inexistente");
		}
	}
}