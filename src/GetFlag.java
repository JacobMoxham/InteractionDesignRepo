import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetFlag{

	
	private static final Pattern pattern = Pattern.compile("<td>The flag is currently <strong>(.+?)</strong></td>");
	private static final String page = "http://www.cucbc.org/flag";
	
	public static Flag FlagColour() throws FlagNotFoundExeption 
	{
		String flagColour = null;
		
	    try 
	    {
	        URLConnection connection = new URL(page).openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line;
	        while ((line = in.readLine()) != null) 
	        {
	        	
	        	if(line.contains("The flag is currently"))
	        	{
	        		final Matcher matcher = pattern.matcher(line);
	        		if (matcher.find()){
	        			flagColour = matcher.group(1).toString();
	        			break;
	        		}
	        	}
	        	else
	        	{
	        		throw new FlagNotFoundExeption("Today we won't have the colour of flag. :/");
	        	}
	        }
	        in.close();
	    } 
	    catch (IOException e) 
	    {
	        System.out.println("Can't open the website");
	    }
	    
	    if(flagColour.toLowerCase().equals("green")) return Flag.GREEN;
	    else if(flagColour.toLowerCase().equals("yellow")) return Flag.YELLOW;
	    else if(flagColour.toLowerCase().equals("red")) return Flag.RED;
	    else if(flagColour.toLowerCase().equals("red/yellow")) return Flag.REDYELLOW;
	    else if(flagColour.toLowerCase().equals("not operational")) return Flag.NOTOPERATIONAL;
	    else return null;
	}	

}