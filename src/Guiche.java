public class Guiche {
	int numero;
	boolean disponivel;
	int tempoAtendimento;
	int contadorDeAtendimento;
	
	public Guiche(int numero, int atendimento){
		this.numero = numero;
		this.disponivel = true; //todo guichê começa vazio
		this.tempoAtendimento = atendimento; //atendimento é os ciclos
		this.contadorDeAtendimento = 0;
	}
	
	public void changeStatus(){
		if(disponivel == true){
			this.disponivel = false;
		} else {
			this.disponivel = true;
		}
	}
	
	public void printStatus(){
		if(this.disponivel){System.out.print("O\n");}
		else{System.out.print("X\n");}
	}
	
	public void verificaCiclo(){
		if(disponivel == false && contadorDeAtendimento == tempoAtendimento){
			changeStatus();
			contadorDeAtendimento = 0; //volta ao normal: livre e com cont = 0. Pode atender clientes
			System.out.println("O Guichê " + numero + " foi desocupado.");
		}
	}
}
