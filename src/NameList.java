import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class NameList {
	
	ArrayList<NameList> nomes;
	private String ocorrencia;
	
	public NameList(String ocorrencia){
		this.ocorrencia = ocorrencia; //a namelist tem entradas do tipo String
		this.nomes = new ArrayList<NameList>();
	}
	
	public String getNome() {
		return ocorrencia;
	}
	
	public String gerarNome(int random){
		String found = "";
		found = this.nomes.get(random).getNome(); //meu Deus.... demorei 1/2 hora pra montar essa linha.
		return found;
	}
	
	public void carregarNomes() {
        Path path = Paths.get("X:\\coding\\Modularizacao\\src\\aedT1\\newNameList.txt"); //pega direto na source
         try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
           String linha = null;
           while ((linha = br.readLine()) != null) {   
              // separador: :
              Scanner nameSniffer = new Scanner(linha).useDelimiter(";");
              
              //System.out.println(nameSniffer.next());
              
              
              NameList nomesEncontrados = new NameList(nameSniffer.next());

              this.nomes.add(nomesEncontrados);
           }
           /*
           for(NameList n : this.nomes){
        	   System.out.println(n.getNome());
           }*/
           
           System.out.println("[x] 400 nomes importados de newNameList.txt");
        } 
        catch (IOException e) {
        		System.err.format("Erro de E/S: %s%n", e);
        } 

         
    }


}
