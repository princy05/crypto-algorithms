import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SenderClass {

	public String plainText = "";
	public int offset = 0;
	public String key = "";
	public int packetOrder = 0;
	Assignment assignment = new Assignment();
	String[][] packet;
	String [][] encryptPacket;
	String hashValue = "";

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SenderClass() throws IOException {
		getSenderInput();
	}

	public int getPacketOrder() {
		return packetOrder;
	}

	public void setPacketOrder(int packetOrder) {
		this.packetOrder = packetOrder;
	}

	public String[][] getEncryptPacket() {
		return encryptPacket;
	}

	public void setEncryptPacket(String[][] encryptPacket) {
		this.encryptPacket = encryptPacket;
	}

	public String getPlainText() {
		return plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	/**
	 * This method allows the user to enter plaintext, key, offset and packet order value through console 
	 * @throws IOException
	 */
	private void getSenderInput() throws IOException {

		System.out.println("**************Sender Interface*****************");
		
		//This gets the plaintext from the users
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Plain text: ");
		plainText = br.readLine();
		setPlainText(plainText);
		System.out.println("There are " + plainText.length() +" bytes of data");
		Scanner scanner = new Scanner(System.in);
		//This gets the key value from the user
		System.out.println("Enter Input Key: ");
		key = scanner.nextLine();
		setKey(key);
		//This gets the offset value from the user
		System.out.println("Enter Offset Value: ");
		offset = scanner.nextInt();
		setOffset(offset);
		//This gets the packet order value from the user
		System.out.println("Input 0: to send the packets in the right order from 0,1,2,3");
		System.out.println("Input 1: to send the packets in the order of 1,0,2,3");
		System.out.println("Input 2: to send the packets in the reverse order from 3,2,1,0");
		System.out.println("Input packet transmitting order: ");
		packetOrder = scanner.nextInt();
		setPacketOrder(packetOrder);

	}

	/**
	 * This method performs the encryption algorithm process for the plaintext on the sender side. A hash value is generated using RC4-BHF technique and
	 * appended to the data segments of each packet for encryption
	 */
	public void encrypt() {
		
		/** this method appends padding 1's followed by as many zeros as possible to the input message 
		 * and produces an appended plain text which is a multiple of 252
		 */
		String appendedText = assignment.readPackets(plainText);
		System.out.println("\n Plaintext after performing initial padding operation");
		System.out.println(appendedText);
		System.out.println("\n There are " + appendedText.length()+" bytes of data' \n");

		//append sequence counter from 0 and increase by 1 to each data segments to produce 256 byte data packet
		packet = assignment.packets(appendedText);
	
		String appendedSCData = "------- Result of Packet after appending SC to message -------- \n";
		for (int y = 0; y <= (packet.length - 1); y++) 
		{  
			String data = packet[y][0];   
			appendedSCData += data + " --- 'The size is "+ data.length()+ " bytes'\n";
		}
		System.out.println(appendedSCData);

		String encryptedData="";               
		encryptPacket = new String[packet.length][2];

		for (int x = 0; x <= (packet.length - 1); x++) 
		{  
			//storing the sequence counter in the packet array
			encryptPacket[x][0]= packet[x][1];
			//this holds data segment along with sequence counter value
			String data = packet[x][0];           
			//generate hash value using data segment and sequence counter value
			hashValue = assignment.hashValue(data,offset);

			//use RC4 encryption algorithm to encrypt data and hash value
			encryptedData = assignment.encryptionOperation(packet[x][2]+hashValue,key);            
			encryptPacket[x][1] = encryptedData;
			System.out.println("---------- Encrypted message value -------- \n " + encryptedData + "\n");
			System.out.println("**************************************************************************************************************************************************");
		}           
		setEncryptPacket(encryptPacket);      

	}



}
