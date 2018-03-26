import java.util.Random; //gerar idade e buscar nome na NameList
import java.util.Scanner; //ler entradas do teclado

public class App {
	
	public static void main (String [] jm) throws java.io.IOException {
		Scanner in = new Scanner(System.in);

		System.out.print("[x] Atendentes posicionados nos guichês.\n");
        NameList registroDeNomes = new NameList("Chamando builder");
        registroDeNomes.carregarNomes();		
		
		System.out.println("\nDigite o tamanho da fila: ");
		int tamanho = in.nextInt();
		Fila queue = new Fila(tamanho);
		
        System.out.println("\nEsta aplicação funciona por ciclos de execução.\nDigite a chance, de 0 a 10, de um cliente entrar na fila a cada ciclo: ");
        int odds = in.nextInt();
        
        System.out.println("\nDigite a quantidade de ciclos necessários para atender um cliente:");
        int att = in.nextInt();
        //isso é mandado como parâmetro no construtor dos Guichês.
        //vou ter que fazer um método pra propor um refresh na disponibilidade
		Guiche g1 = new Guiche(1, att);
		Guiche g2 = new Guiche(2, att);
		Guiche g3 = new Guiche(3, att);
		Guiche g4 = new Guiche(4, att);
		Guiche g5 = new Guiche(5, att);
		Guiche g6 = new Guiche(6, att);
		Guiche g7 = new Guiche(7, att);
		Guiche g8 = new Guiche(8, att);
		Guiche g9 = new Guiche(9, att);
		Guiche g10= new Guiche(10, att);
		
        System.out.println("\n>SIMULAÇÃO INICIADA<\n");
        
        
		while(true){//nos comentários, restos mortais do menu
			
			//ENTRADA DE CLIENTES NA FILA
			if(generator(3) <= odds){ //se odds = 7 (70%) e GDS <= 7 (0 a 70%)
				
				String nome = registroDeNomes.gerarNome(generator(1));
				int idade = generator(2);
					
				queue.put(nome, idade);
			} 
			else{
				System.out.println("Ninguém entrou na fila durante este ciclo de execução.");
			}
			//ATENDIMENTO DOS GUICHÊS
			if(queue.putIndice == 0){System.out.println("Não há alguém para ser atendido.\n");} //se não tem ninguém na fila. tá dando direitinho
			
				else if(g1.disponivel == false && //se todos os guichês estão ocupados
						g2.disponivel == false &&
						g3.disponivel == false &&
						g4.disponivel == false &&
						g5.disponivel == false &&
						g6.disponivel == false &&
						g7.disponivel == false &&
						g8.disponivel == false &&
						g9.disponivel == false &&
						g10.disponivel == false
						){System.out.println("Todos os guichês estão ocupados.\n");//isso tá dando direitinho
						}
							else{ //se o atendimento é possível -> algum desses é true, se passou pelo else if anterior..
								String msg = "";
									   		   if(g1.disponivel) { msg = "Guichê 1: Preferencial. "; queue.realocarPreferencial(); g1.changeStatus();
										} else if(g2.disponivel) { msg = "Guichê 2: Preferencial. "; queue.realocarPreferencial(); g2.changeStatus(); 
										} else if(g3.disponivel) { msg = "Guichê 3: Preferencial. "; queue.realocarPreferencial(); g3.changeStatus(); 
										} else if(g4.disponivel) { msg = "Guichê 4: Preferencial. "; queue.realocarPreferencial(); g4.changeStatus(); 
										} else if(g5.disponivel) { msg = "Guichê 5: Preferencial. "; queue.realocarPreferencial(); g5.changeStatus(); 
										} else if(g6.disponivel) { msg = "Guichê 6: Normal. "; g6.changeStatus(); 
										} else if(g7.disponivel) { msg = "Guichê 7: Normal. "; g7.changeStatus(); 
										} else if(g8.disponivel) { msg = "Guichê 8: Normal. "; g8.changeStatus(); 
										} else if(g9.disponivel) { msg = "Guichê 9: Normal. "; g9.changeStatus(); 
										} else if(g10.disponivel){msg = "Guichê 10: Normal. ";g10.changeStatus(); 
										}	

								    System.out.print(msg);
								queue.get();
							}
			
				//IMPRESSÃO DO CENÁRIO
				System.out.print("\nGUICHÊ  1: "); g1.printStatus();
				System.out.print("GUICHÊ  2: "); g2.printStatus();
				System.out.print("GUICHÊ  3: "); g3.printStatus();
				System.out.print("GUICHÊ  4: "); g4.printStatus();
				System.out.print("GUICHÊ  5: "); g5.printStatus();
				System.out.print("GUICHÊ  6: "); g6.printStatus();
				System.out.print("GUICHÊ  7: "); g7.printStatus();
				System.out.print("GUICHÊ  8: "); g8.printStatus();
				System.out.print("GUICHÊ  9: "); g9.printStatus();
				System.out.print("GUICHÊ 10: "); g10.printStatus();
				queue.exibir();
			
			

			//VERIFICAÇÃO DE TEMPO DE ATENDIMENTO NOS GUICHÊS
				
				//se está em atendimento, cont++ para esse ciclo, e já chama a verificação.
				if(!g1.disponivel){g1.contadorDeAtendimento++; g1.verificaCiclo();}
				if(!g2.disponivel){g2.contadorDeAtendimento++; g2.verificaCiclo();}
				if(!g3.disponivel){g3.contadorDeAtendimento++; g3.verificaCiclo();}
				if(!g4.disponivel){g4.contadorDeAtendimento++; g4.verificaCiclo();}
				if(!g5.disponivel){g5.contadorDeAtendimento++; g5.verificaCiclo();}
				if(!g6.disponivel){g6.contadorDeAtendimento++; g6.verificaCiclo();}
				if(!g7.disponivel){g7.contadorDeAtendimento++; g7.verificaCiclo();}
				if(!g8.disponivel){g8.contadorDeAtendimento++; g8.verificaCiclo();}
				if(!g9.disponivel){g9.contadorDeAtendimento++; g9.verificaCiclo();}
				if(!g10.disponivel){g10.contadorDeAtendimento++;g10.verificaCiclo();}
				
			System.out.println("Envie qualquer caractere..");
			waitPress();
		
		}//fecha while
	}//fecha main
	
	
	//parâmetro 1: gerar nome (0 a 399) 
	//parâmetro 2: gerar idade (12 a 90) 
	//parâmetro 3: gerar situação do ciclo (1 a 10)
	public static int generator(int param) {
		
	    Random rand = new Random();
	    int randomNum = 0;
	    //int randomNum = rand.nextInt((max - min) + 1) + min;
	    	 if(param == 1){randomNum = rand.nextInt(400);}
	    else if(param == 2){randomNum = rand.nextInt((90 - 12) + 1) + 12;}
	    else if(param == 3){randomNum = rand.nextInt((10 - 1) + 1) + 1;}
	    
	    //restringi o universo observável pra clientes de 12 a 90 anos

	    return randomNum;
	}
	

	public static String waitPress(){ //eu nem preciso do retorno, mas dava erro de execução se fizesse void...
		Scanner wp = new Scanner (System.in);
		return wp.next();
	}
}//fecha classe
