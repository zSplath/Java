import javax.swing.*;

public class Inicio {

	public static void main(String[] args) {
		
		int opc = 0;
		
		while (opc != 3)
		{
			try
			{
				opc = Integer.parseInt(JOptionPane.showInputDialog
				("Menu Principal"
	            + "\n 1 - Parâmetros do Sistema"  
	            + "\n 2 - Reserva de Passagens"
	            + "\n 3 - Sair"));
				if (opc == 1) parametros();
				if (opc == 2) reserva();
			}
			catch (Exception erro)
			{
				if ((erro.getMessage() == "null") || (erro.getMessage() == "Cannot parse null string")) System.exit(0);
				else
				JOptionPane.showMessageDialog( null," A operação retornou o erro: \n" + erro.getMessage());
			}
		}
		
	}
	
	public static void parametros () {
		int opc = 0;
		
		while (opc != 3)
		{
			try
			{
				opc = Integer.parseInt(JOptionPane.showInputDialog
				("Parâmetros do Sistema"
	            + "\n 1 - Cadastrar Aeronave"  
	            + "\n 2 - Cadastrar Voo"
	            + "\n 3 - Voltar"));
				if (opc == 1) CadastrarAeronave();
				if (opc == 2) CadastrarVoo();
			}
			catch (Exception erro)
			{
				if ((erro.getMessage() == "null") || (erro.getMessage() == "Cannot parse null string")) System.exit(0);
				else
				JOptionPane.showMessageDialog( null," A operação retornou o erro: \n" + erro.getMessage());
			}
		}
	}
	
	public static void CadastrarAeronave () {
		String modelo;
		int fileiras = 0, assentos = 0, teste = 0;
		
		modelo = JOptionPane.showInputDialog(" Digite o modelo da Aeronave: ");
		if (modelo == null) System.exit(0);
		
		while (teste==0)
		{
			try
			{
				fileiras = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de fileiras: "));
				teste = 1;
			}
			catch (Exception erro)
			{
				if ((erro.getMessage() == "null") || (erro.getMessage() == "Cannot parse null string")) System.exit(0);
				else
				JOptionPane.showMessageDialog( null," A operação retornou o erro: \n" + erro.getMessage());
			}
		}
		
		teste = 0;
		
		while (teste==0)
		{
			try
			{
				assentos = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de assentos por fileira: "));
				teste = 1;
			}
			catch (Exception erro)
			{
				if ((erro.getMessage() == "null") || (erro.getMessage() == "Cannot parse null string")) System.exit(0);
				else
				JOptionPane.showMessageDialog( null," A operação retornou o erro: \n" + erro.getMessage());
			}
		}
		
		Global.aero1 = new Aviao(modelo, fileiras, assentos);
		
		JOptionPane.showMessageDialog( null," Aeronave Cadastrada: \n" +
		" Modelo: " + Global.aero1.getModelo() +
        "\n Numero de Fileiras: " + Global.aero1.getFileiras() +
        "\n Numero de Assentos por fileira: " + Global.aero1.getAssentos());
	}

	public static void CadastrarVoo () {
		int numero = 0, teste = 0;
		String data, hora;
		
		if (Global.arraydovoo == 10) return;
		
		if (Global.aero1 == null)
		{
			JOptionPane.showMessageDialog( null," Nenhuma Aeronave foi cadastrada   \n "
							+ "Primeiro cadastre uma Aeronave   ");
			return;
		}
		
		while (teste==0)
		{
			try
			{
				numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero do voo: "));
				
				boolean repetido = false;
				
				for(int i = 0; i < 10; i++)
				{
					if (Global.viagens[i] == null) i = 12;
					else if(Global.viagens[i].getNro() == numero) repetido = true;
							
				}
				
				if (repetido == false)
					teste = 1;
				else JOptionPane.showMessageDialog( null," O numero de Voo \"" + numero + "\" Já existe" );
			}
			catch (Exception erro)
			{
				if ((erro.getMessage() == "null") || (erro.getMessage() == "Cannot parse null string")) System.exit(0);
				else
				JOptionPane.showMessageDialog( null," A operação retornou o erro: \n" + erro.getMessage());
			}
		}
		
		data = JOptionPane.showInputDialog("Digite a data do voo: ");
		if (data == null) System.exit(0);
		hora = JOptionPane.showInputDialog("Digite o horario do Voo: ");
		if (hora == null) System.exit(0);
				
		Aviao aviaotemp = new Aviao(Global.aero1.getModelo(), Global.aero1.fileiras, Global.aero1.assentos);
		
		Voo aux = new Voo(aviaotemp, numero, data, hora);
		
		Global.viagens[Global.arraydovoo] = aux;
		
		Global.arraydovoo++;
	}
	
	public static void reserva () {
        int opc = 0;
		
        while (opc != 4 && opc != 5)
        {
	        try
			{
				Global.numerovoo = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero do voo: "));	
			}
			catch (Exception erro)
			{
				if ((erro.getMessage() == "null") || (erro.getMessage() == "Cannot parse null string")) System.exit(0);
				else
				JOptionPane.showMessageDialog( null," A operação retornou o erro: \n" + erro.getMessage());
			}
	        
	        for (int i=0; i < 10; i++)
	        	if (Global.viagens[i] == null) i = 10;
	        	else if (Global.numerovoo == Global.viagens[i].getNro())
		        	{
	        			Global.numerovoo = i;
	        			opc = 5; 
	        			i = 10; 
		        	}

	        if ( opc == 0 ) 
	        	JOptionPane.showMessageDialog( null," Voo não encontrado");
        }
        
		while (opc != 4)
		{
			try
			{
				opc = Integer.parseInt(JOptionPane.showInputDialog
				("Reserva de Passagens"
				+ "\n Voo: " + Global.viagens[Global.numerovoo].getNro()
	            + "\n 1 - Fazer reserva"  
	            + "\n 2 - Consultar lugares vazios"
	            + "\n 3 - Consultar reservas realizadas"
	            + "\n 4 - Voltar"));
		
				
				if (opc == 1)
				
					reservaPassagem();
				
				if (opc == 2)
				
					mostraLugares();
				
				if (opc == 3)
				
					mostraReservados();
				
				if (opc != 1 && opc != 2 && opc != 3 && opc != 4)
					JOptionPane.showMessageDialog(null, "Opção inválida");
			}
			catch (Exception erro)
			{
				if ((erro.getMessage() == "null") || (erro.getMessage() == "Cannot parse null string")) System.exit(0);
				else
				JOptionPane.showMessageDialog( null," A operação retornou o erro: \n" + erro.getMessage());
			}
			
			
		}
	}
	
	public static void reservaPassagem () {
		
		Aviao lugaresdovoo = Global.viagens[Global.numerovoo].getAeronave();
		int contador = 0;
		
		for (int i = 0; i < lugaresdovoo.fileiras; i++)
			for (int j = 0; j < lugaresdovoo.assentos; j++)
				if (lugaresdovoo.verificaLugarOcupado(i, j) == false)
					contador++;
		
		if (contador == 0)
		{
			JOptionPane.showMessageDialog( null, "Erro: Não há lugares disponíveis");
			return;
		}
		
		String nomereservista, cpfreservista;
		
		nomereservista = JOptionPane.showInputDialog("Digite o nome do Passageiro: ");
		if (nomereservista == null) System.exit(0);
		cpfreservista  = JOptionPane.showInputDialog("Digite o CPF do Passageiro: ");
		if (cpfreservista == null) System.exit(0);
		
		Passageiro reservista = new Passageiro(nomereservista, cpfreservista);
		
		int teste = 0, teste2 = 0, teste3 = 0;
		int fileira = 0, assento = 0;
		
		while (teste3 == 0)
		{
			while (teste == 0)
			{
				try
				{
					while (teste2 == 0)
					{
						fileira = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da fileira: ")) -1;
						if (lugaresdovoo.fileiras > fileira) teste2 = 1;
						else JOptionPane.showMessageDialog( null," A fileira " + ++fileira + " não existe");
					}
					teste = 1;
				}
				catch (Exception erro)
				{
					if ((erro.getMessage() == "null") || (erro.getMessage() == "Cannot parse null string")) System.exit(0);
					else
					JOptionPane.showMessageDialog( null," A operação retornou o erro: \n" + erro.getMessage());
				}
			}
			
			teste = 0;
			teste2 = 0;
			
			while (teste==0)
			{
				try
				{
					while (teste2 == 0)
					{
						assento = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero do assento: ")) -1;
						if (lugaresdovoo.assentos > assento) teste2 = 1;
						else JOptionPane.showMessageDialog( null," O assento " + ++assento + " não existe");
					}
					teste = 1;
				}
				catch (Exception erro)
				{
					if ((erro.getMessage() == "null") || (erro.getMessage() == "Cannot parse null string")) System.exit(0);
					else
					JOptionPane.showMessageDialog( null," A operação retornou o erro: \n" + erro.getMessage());
				}
			}
			
			if (lugaresdovoo.verificaLugarOcupado(fileira, assento) == false) teste3 = 1;
			else 
			{
				JOptionPane.showMessageDialog( null," O assento " + ++assento + " da fileira " + ++fileira + "\n Está ocupado por "
						+ lugaresdovoo.lugares[--fileira][--assento].getNome() 
						+ "\n CPF: " + lugaresdovoo.lugares[fileira][assento].getCpf() ); 
			teste = 0; teste2 = 0;
			}
		}
		
		lugaresdovoo.setPassageiro(fileira, assento, reservista);
		
		Global.viagens[Global.numerovoo].setAeronave(lugaresdovoo);
	}
	
	public static void mostraLugares () {
		
		Aviao lugaresdovoo = Global.viagens[Global.numerovoo].getAeronave();
		int contador = 0;
		
		for (int i = 0; i < lugaresdovoo.fileiras; i++)
			for (int j = 0; j < lugaresdovoo.assentos; j++)
				if (lugaresdovoo.verificaLugarOcupado(i, j) == false)
					contador++;
		
		JOptionPane.showMessageDialog( null, contador + " Lugares estão vazios");
	}
	
	public static void mostraReservados() {
		
		Aviao lugaresdovoo = Global.viagens[Global.numerovoo].getAeronave();
		String matriz = "";
		
		for (int i = 0; i < lugaresdovoo.fileiras; i++)
		{
			for (int j = 0; j < lugaresdovoo.assentos; j++)
				if (lugaresdovoo.verificaLugarOcupado(i, j) == false)
					matriz += "[  ]";
				else matriz += "[X]";
			
			matriz += "\n";
		}
		
		JOptionPane.showMessageDialog( null, "Voo: " + Global.viagens[Global.numerovoo].getNro() +
											 "\nData: " + Global.viagens[Global.numerovoo].getData() +
											 "\nHora: " + Global.viagens[Global.numerovoo].getHora() +
											 "\n" + matriz);
	}
}