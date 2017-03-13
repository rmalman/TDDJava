import static org.junit.Assert.*;

import org.junit.Test;

public class TextHandler {

	@Test
	public void test() {
		int wordCount = 0;
		int pipeCount = 0;
		int digitCount = 0;
		
        String s = " 1 9    79   Olá,      meu   nome  é raphael    ";
        String BuildText = "";
        boolean word = false;
        int endOfLine = s.length() - 1;
        
        s = s.replaceAll("\\s","|");

        for (int i = 0; i < s.length(); i++) {

            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                word = true;     
                if(pipeCount > 0)
                {
                	BuildText += "%"+String.valueOf(pipeCount)+"%";
                	pipeCount = 0;
                }
                BuildText += s.charAt(i); 
            } else if (!Character.isLetter(s.charAt(i)) && word) {
                wordCount++;
                word = false;
                if (s.charAt(i) != '|')
                	BuildText += s.charAt(i);
                if(pipeCount > 0)
                	BuildText += "%"+String.valueOf(pipeCount)+"%";
                pipeCount = 0;
            }else if (Character.isDigit(s.charAt(i))) {
                digitCount++;
                if(pipeCount > 0)
                	BuildText += "%"+String.valueOf(pipeCount)+"%";
                pipeCount = 0;
                BuildText += s.charAt(i);                
            }else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
                if(pipeCount > 0)
                	BuildText += "%"+String.valueOf(pipeCount)+"%";
                pipeCount = 0;
            }
            if(s.charAt(i) == '|'){
            	pipeCount++;
            	System.out.println("Pipecount: [" + String.valueOf(pipeCount) + "] após ["+String.valueOf(wordCount)+"]a. palavra ");
            	if(i == endOfLine)
            		BuildText += "%"+String.valueOf(pipeCount)+"%";
            }
        }
        System.out.println("Quantidade de palavras: [" + String.valueOf(wordCount) + "]");
        System.out.println("Quantidade de numeros: [" + String.valueOf(digitCount) + "]");
        System.out.println("Texto Concluido: [" + BuildText + "]");
        System.out.println("Texto Inicial: [" + s + "]");

	}

}
