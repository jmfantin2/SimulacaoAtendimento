public class Fila {
	Cliente fila[];
	int putIndice;	//em que posi��o o pr�ximo cliente ser� inserido (0 a tamanho-1)
	
	public Fila( int tamanho ){ //construtor da fila
		fila = new Cliente[ tamanho ];
		putIndice = 0; 
	}

	
	public void put( String nome, int idade){ //insere elemento
		if( putIndice == fila.length ){
			System.out.println( "Fila cheia! " + nome + " (" + idade + ") " + "n�o p�de entrar.\n");
			return;
		}
		Cliente putCliente = new Cliente(nome, idade);
		fila[putIndice++] = putCliente;
		System.out.println( "Cliente " + putCliente.getNome() + " (" + idade + ") " + "entrou na fila.\n" );
		exibir();
	}


	public boolean get(){ //remove elemento (atende cliente)
		//se o atendimento for preferencial, a main j� chamou fila.realocarPreferencial();
		//e o primeiro idoso encontrado � o elemento [0]
		
		/*if( putIndice == 0 ){						nem entra nesse if, pq confere na main se o putIndice == 0
			System.out.println( "Fila vazia!!!\n");
			return false;
		}*/
			Cliente clienteAtendido = fila[0];
			for( int pos = 1; pos < putIndice; pos++ ){
				fila[ pos - 1 ] = fila[ pos ];
			}
	
			fila[ (putIndice - 1) ] = null;
			putIndice--;
			
			System.out.println("Cliente " + clienteAtendido.getNome() + ", de " + clienteAtendido.getIdade() + " anos, est� sendo atendido(a).");
			
			refreshQueue();//gambiarra alert! 
			//forcei o �ndice a se igualar a zero caso todo mundo tenha sido atendido. N�o tava conseguindo resolver sem isso.
			return true;
		
	}


	public void exibir(){ //exibe guich�s e idades dos clientes na fila
		for( int cont = 0; cont < fila.length; cont++ ){
			if(fila[cont] == null){System.out.print( "[    ]" );}
			else{System.out.print( "[ " + fila[cont].getIdade() + " ]" );}
		}
		System.out.println("\n");
	}
	
	public void realocarPreferencial(){ //� chamado se guiche <= 5

		if(fila[0].getIdade() <= 64){//s� procura por um idoso se o primeiro da fila n�o for idoso
			Cliente aux = null;
			for(int i = 1; i < fila.length; i++){
				if(fila[i] == null){
					break;  //se chegou em espa�o vago de fila, vai-te embora.
				}
				if(fila[i].getIdade() >= 65){// SE FINALMENTE ACHOU UM IDOSO
					aux = fila[i]; //auxiliar recebe o idoso
					fila[i] = null; //idoso deixa lugar vago
					for(int j = i; j >= 1; j--){//manda todo mundo na frente do idoso 1 pra tr�s
						fila[j] = fila[j-1];
					}//nesse momento, fila[0] e fila[1] s�o clones
					fila[0] = aux; //aux entra � for�a em fila[0]
					break;//agora que j� botou o idoso em fila[0], vai-te embora
					
				}//todo mundo no lugar certo
				
			}
			//parou de procurar idoso, e se aux n�o recebeu idoso:
			if(aux == null){System.out.println("N�o h� um idoso na fila.");}
			//nada acontece
		}
		System.out.print("\n");
	}
	
	public void refreshQueue(){
		if(fila[0] == null){
			putIndice = 0;
		}
	}
	
}
