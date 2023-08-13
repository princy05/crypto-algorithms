import java.util.Scanner;

public class ReceiverClass {

	public int roffset = 0;
	public String rkey = "";
	SenderClass sender;
	Assignment assignment = new Assignment();
	public String skey = ""; 
	public int soffset = 0;
	public int order = 0; 
	public String [][] encryptedPacket;
	public String plaintext = "";

	public ReceiverClass(SenderClass sender) {
		this.sender = sender;
		getReceiverInput();
	}

	/**
	 * This method is used to receive input data from receiver side. It gets receiver key and offset value from the user through console input
	 */
	public void getReceiverInput() {

		System.out.println("\n*********************Receiver Interface**********************");
		try(Scanner scanner = new Scanner(System.in)){
			System.out.println("Enter key for receiver: ");
			rkey = scanner.nextLine();
			System.out.println("Enter Offset Value: ");
			roffset = scanner.nextInt();
		}
	}

	/**
	 * This method performs the decryption operation on the encrypted data. It also checks whether the sender key and offset value matches
	 * with the receiver side key and offset value. If it matches it then check for the packet order and encrypts the cipher text. The cipher text 
	 * contains both data segment and hash value. It compares the sender hash value and the receiver hash value to verify the message received on the
	 * receiver side is reliable or not
	 */
	public void decrypt() {

		String result ="";
		//this gets the value of sender side
		skey = sender.getKey();
		soffset = sender.getOffset();
		order = sender.getPacketOrder();
		encryptedPacket = sender.getEncryptPacket();
		plaintext = sender.getPlainText();
		System.out.println("Sender key: "  + skey);
		System.out.println("Receiver key: " + rkey);

		String outputText= "";
		String temp2= "";
		String temp3= "";
		String [] res = new String[3];

		//Check whether the sender key and offset value matches with the receiver side key and offset value
		if ((rkey.equals(skey)) && (roffset == soffset))
		{
			//checks for the packet transmitting order
			if(order==0)
			{
				System.out.println("\n Packets Recieved from sender with Key = " +rkey+" Offset="+roffset);

				for (int x = 0; x < encryptedPacket.length; x++)
				{

					temp3+=x+",";

					String decryptedData = assignment.encryptionOperation(encryptedPacket[x][1],rkey);
					System.out.println("\n Decrypted packet " + x +" which contains data and hash value : \n");

					result+="<<Sequence Counter value>>"+ encryptedPacket[x][0] + "<</Sequence Counter value>>\n";
					result+="<<Decrypted Data>> "+ decryptedData.substring(0,252)+ "<</Decrypted Data>>";
					res = checkCompromise(decryptedData,x);
					result+=res[0];
					System.out.println(result);
					result = "";

					if(res[2].equals("false"))
					{
						String output =res[0]+res[1];
						System.out.println(output);
						break;

					}
					System.out.println("**************************************************************************************************************************************************");

				}                
				System.out.println(" packet are received in the order of "+temp3.substring(0, temp3.length()-1)+"\n" );
			}
			else if(order==1)
			{
				System.out.println("\n Packets Recieved from sender with Key = " +rkey+" Offset="+roffset);
				String c="";                

				for (int x = 1; x >= 0; x--)
				{
					temp3+=x+",";
					String decryptedData = assignment.encryptionOperation(encryptedPacket[x][1],rkey);
					System.out.println("\n Decrypted packet " + x +" which contains data and hash value : \n");
					result+="<<Sequence Counter value>>"+ encryptedPacket[x][0] + "<</Sequence Counter value>>\n";
					result+="<<Decrypted Data>> "+ decryptedData.substring(0,252)+ "<</Decrypted Data>>";
					res = checkCompromise(decryptedData,x);
					result+=res[0];
					System.out.println(result);
					result = "";

					if(res[2].equals("false"))
					{
						String output =res[0]+res[1];
						System.out.println(output);
						c="false";
						break;

					}

					System.out.println("**************************************************************************************************************************************************");

				}
				for (int x = 2; x < encryptedPacket.length; x++)
				{
					temp3+=x+",";
					if(c.equals("false"))
					{                        
						break;                        
					}

					String decryptedData = assignment.encryptionOperation(encryptedPacket[x][1],rkey);
					System.out.println("\n Decrypted packet " + x +" which contains data and hash value : \n");
					result+="<<Sequence Counter value>>"+ encryptedPacket[x][0] + "<</Sequence Counter value>>\n";
					result+="<<Decrypted Data>> "+ decryptedData.substring(0,252)+ "<</Decrypted Data>>";
					res = checkCompromise(decryptedData,x); 
					result+=res[0];
					System.out.println(result);
					result = "";

					if(res[2].equals("false"))
					{
						String output =res[0]+res[1];
						System.out.println(output);
						break;

					}

					System.out.println("**************************************************************************************************************************************************");

				}               
				System.out.println(" packet are received in the order of "+temp3.substring(0, temp3.length()-1)+"\n" );
			}
			else if(order==2)
			{
				System.out.println("\n Packets Recieved from sender with Key = " +rkey+" Offset="+roffset);
				for (int x =(encryptedPacket.length-1); x >=0 ; x--)
				{
					temp3+=x+",";
					String decryptedData = assignment.encryptionOperation(encryptedPacket[x][1],rkey);
					System.out.println("\n Decrypted packet " + x +" which contains data and hash value : \n");
					result+="<<Sequence Counter value>>"+ encryptedPacket[x][0] + "<</Sequence Counter value>>\n";
					result+="<<Decrypted Data>> "+ decryptedData.substring(0,252)+ "<</Decrypted Data>>";
					res = checkCompromise(decryptedData,x);
					//temp3+=res[0];
					result+=res[0];
					System.out.println(result);
					result = "";
					if(res[2].equals("false"))
					{
						String output =res[0]+res[1];
						System.out.println(output);
						break;

					}

				}                
				System.out.println(" packet are received in the order of "+temp3.substring(0, temp3.length()-1)+"\n" );
			}
			else
			{
				System.out.println("\n Packets Recieved from sender with Key = " +rkey+" Offset="+roffset);
				for (int x = 0; x < encryptedPacket.length; x++)
				{

					String decryptedData = assignment.encryptionOperation(encryptedPacket[x][1],rkey);
					System.out.println("\n Decrypted packet " + x +" which contains data and hash value : \n");
					result+="<<Sequence Counter value>>"+ encryptedPacket[x][0] + "<</Sequence Counter value>>\n";
					result+="<<Decrypted Data>> "+ decryptedData.substring(0,252)+ "<</Decrypted Data>>";
					res = checkCompromise(decryptedData,x);
					result+=res[0];
					System.out.println(result);
					result = "";
					if(res[2].equals("false"))
					{
						String output =res[0]+res[1];
						System.out.println(output);
						break;

					}                                

				}                

			}

			if (res[2]=="true")
			{
				outputText+="\n\nPlaintext Received from the sender \n";
				for (int x = 0; x < encryptedPacket.length; x++)
				{
					String decryptedData = assignment.encryptionOperation(encryptedPacket[x][1],rkey);
					temp2+=decryptedData.substring(0,252);

				}
				outputText+=temp2.substring(0,plaintext.length());
			}
		}
		else
			System.out.println("There is no message found with Key = " +rkey+" Offset="+roffset);

		System.out.println(outputText);

	}

	/**
	 * This method checks whether the hash value has been compromised on the receiver side. If it is compromised then ask the sender to
	 * resend the input message again to the receiver
	 * @param decryptedData
	 * @param y
	 * @return
	 */
	public String[] checkCompromise(String decryptedData, int y) {

		String shash = decryptedData.substring(252);
		String dplain = decryptedData.substring(0,252);
		String rhash = assignment.hashValue("000"+y+dplain,soffset);
		String [] res=new String[3];

		res[0]="\n<< Calculated Hash>> "+ rhash+ "<<Calculated Hash>>";


		boolean stat = comparehash(shash,rhash);        
		//returns true if both hash value matches 
		if (stat==true)
		{
			res[0]+="<<Received Hash>> "+ shash+ "<</Received Hash>>\n";
			//res[1]="\n<<SC>>"+y+ "<</SC>><<Decrypted Data+Hash>>"+ dec+"<</Decrypted Data+Hash>>\n";
			res[2]= "true";
		}
		//if the hash value does not match perform the else block step
		else
		{
			res[0]+="<<Received Hash>> "+ shash+ "<</Received Hash>>\n";
			res[1]= "\nMessage has been compromised. Please request Sender to resend the input message again \n";
			res[2] = "false";
		}
		return res;

	}

	/**
	 * This method compares the sender side hash value with the receiver side hash value
	 * @param shash
	 * @param rhash
	 * @return
	 */
	public boolean comparehash(String shash, String rhash) {

		if (shash.equals(rhash))
			return true;
		else
			return false;

	}

}
