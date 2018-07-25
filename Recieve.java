import java.util.List;
import java.util.ArrayList;

public class Recieve {
	
	public static final String SCRIPT = "recievemessage.php";
	
	public static void recieveMessage(){
		
		List<String> parameters = new ArrayList<String>();
		parameters.add("access_token");parameters.add(Chat.accessToken);
		String query = Encode.encodeURL(parameters);
		
		String result = ConnectServer.runOnServer(SCRIPT,query);
		
		//Check the response from the server 
		if(result == null){
			System.out.println("ERROR-Receive1 : The message could not be received.");
		}
		else if(result.equals("0")){
			//Do nothing.
			//This case means that there are no more messages for the current user.
		}
		else{
			//This case means that there is a message for you.
			//System.out.println("Message Recieved :-"+result);
			parseRecievedData(result);
		}	
	}
	
	public static void parseRecievedData(String result){
		
		System.out.println("Result = "+result);
		
		String[] numberOfMessages = result.split("####");
		//System.out.println("Message Recieved :-"+result);
		for(String i:numberOfMessages){
			String[] sep = i.split("##");
			//System.out.println("SEP LEN = "+sep.length);
			
			if(sep.length==2)
				System.out.println("\nMessage from user = "+sep[0]+" ---- Message = "+Crypt.decrypt(sep[1]));
			//else
				//System.out.println("\nInvalid Message Type received.");
		}
		System.out.print("Enter Message: ");
		
	}

}
