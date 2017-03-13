import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TextHandler {

	@Test
	public void test() {
		int wordCount = 0;
		int pipeCount = 0;
		int digitCount = 0;
		int prevWordCount = 0;
		int prevDigitCount = 0;
		
        String s = "# WST      0000          429999                                                      05716151710                                                                                                                       99394            undefined           99394                                                                     #";
        String BuildText = "";
        boolean word = false;
        int endOfLine = s.length() - 1;
        
        s = s.replaceAll("\\s","|");

        for (int i = 0; i < s.length(); i++) {

            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                word = true;     
                if(pipeCount > 0)
                {
                	if(pipeCount > 2)
                		BuildText += "%"+String.valueOf(pipeCount)+"%";
                	else
                	{
                		for(int x = 0; x < pipeCount; x++)
                		{
                			BuildText += " ";
                		}
                	}
                	pipeCount = 0;
                }
                BuildText += s.charAt(i); 
            } else if (!Character.isLetter(s.charAt(i)) && word) {
                wordCount++;
                word = false;
                if (s.charAt(i) != '|')
                	BuildText += s.charAt(i);
                if(pipeCount > 0)
                	{
                		if(pipeCount > 2)
                			BuildText += "%"+String.valueOf(pipeCount)+"%";
                		else
                		{
                			for(int x = 0; x < pipeCount; x++)
                			{
                				BuildText += " ";
                			}
                		}                	
                	}
                pipeCount = 0;
            }else if (Character.isDigit(s.charAt(i))) {
                digitCount++;
                if(pipeCount > 0)
                	{
                		if(pipeCount > 2)
                			BuildText += "%"+String.valueOf(pipeCount)+"%";
                		else
                		{
                			for(int x = 0; x < pipeCount; x++)
                			{
                				BuildText += " ";
                			}
                		}                	
                	}
                pipeCount = 0;
                BuildText += s.charAt(i);                
            }else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
                if(pipeCount > 0)
                	{
                		if(pipeCount > 2)
                			BuildText += "%"+String.valueOf(pipeCount)+"%";
                		else
                		{
                			for(int x = 0; x < pipeCount; x++)
                			{
                				BuildText += " ";
                			}
                		}
                	}
                pipeCount = 0;
            }
           else if (!Character.isDigit(s.charAt(i))&& !Character.isLetter(s.charAt(i))) 
            {
        	   Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        	   Matcher m = p.matcher(String.valueOf(s.charAt(i)));
        	   boolean b = m.find();

        	   if (b && s.charAt(i) != '|')
        	   {
        		   if(pipeCount > 0)
               		{
        			   if(pipeCount > 2)
        				   BuildText += "%"+String.valueOf(pipeCount)+"%";
               		   else
               		   {
               			   for(int x = 0; x < pipeCount; x++)
               			   {
               				   BuildText += " ";
               			   }
               		   }
        			   pipeCount = 0;
               		}   
        		   
        		   BuildText += s.charAt(i);
        	   }
            }
           
            if(s.charAt(i) == '|'){
            	pipeCount++;
            	if(i == endOfLine)
            		{
            			if(pipeCount > 2)
            				BuildText += "%"+String.valueOf(pipeCount)+"%";
            			else
            			{
            				for(int x = 0; x < pipeCount; x++)
            				{
            					BuildText += " ";
            				}
            			}
            		}
            }
        }
        System.out.println("Quantidade de palavras: [" + String.valueOf(wordCount) + "]");
        System.out.println("Quantidade de numeros: [" + String.valueOf(digitCount) + "]");
        System.out.println("Texto Concluido: [" + BuildText + "]");
        System.out.println("Texto Inicial: [" + s + "]");
        System.out.println("Size Concluido: [" + BuildText.length() + "]"); 
        System.out.println("Size Inicial: [" + s.length() + "]");

	}

}
