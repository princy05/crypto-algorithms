import java.io.IOException;
import java.util.ArrayList;

public class Assignment {
	public static void main(String args[]) throws IOException {

		/** 
		 * This no-arg constructor calls the sender interface which enables the
		 * user to enter the input message, key, offset and packet order values 
		 **/
		SenderClass sender = new SenderClass();

		/** 
		 * This method appends sequence counter and hash value to each data segments of 252 bytes data
		 * The hash value is obtained through three steps : 1) Appending and Padding, 2) Compression and 
		 * 3) Truncation. It then performs the encryption algorithm on the data segment and hash value
		 */		
		sender.encrypt();

		/**
		 * This constructor for Receiver interface passes the Sender interface object. It also allows 
		 * the user to enter the receiver key and offset
		 */
		ReceiverClass receiver = new ReceiverClass(sender);

		/**
		 * This method performs the decryption operation on the encrypted data. It compares the receiver side key and
		 * offset value with sender side key and offset value. It also compares the hash value of sender side with
		 * the receiver side and produces the decrypted data packets in the packet order entered by the user from the sender side
		 */
		receiver.decrypt();
	}

	/**
	 * This method divides the input packet and performs padding operation if there are no enough data in the data segment of the last data packet
	 * @param data
	 * @return
	 */
	public String readPackets(String data) {

		int lastPacketContent = data.length() % 252;
		if (lastPacketContent != 0) 
		{
			String bit= "10000000";
			char paddedCharacter = ConvertBinaryToChar(bit);
			data+=paddedCharacter;
			lastPacketContent = data.length() % 252;

		}

		if (lastPacketContent != 0) 
		{
			for (int pad = 0; pad < (252 - lastPacketContent); pad++) 
			{
				String bit= "00000000";
				char paddedCharacter = ConvertBinaryToChar(bit);                
				data+=paddedCharacter;                
			}

		}

		return data;

	}

	/**
	 * This method converts the binary input to char output
	 * @param binaryData
	 * @return
	 */
	public char ConvertBinaryToChar(String binaryData) {
		int bin = Integer.parseInt(binaryData,2); // parses the string argument to integer value
		char charEquivalent = (char)(bin);
		return charEquivalent;
	}

	/** This method appends sequence counter SC of 4 bytes value to the data segment of 252 bytes data 
	 * to produce a 256 bytes. The sequence counter (SC) value is increased by 1 in increased order (initially SCA = 0).
	 */
	public String[][] packets(String paddedData) {

		String[] t = new String[2];         
		java.util.ArrayList<String[]> packet = new java.util.ArrayList<String[]>();

		int num_segments = (paddedData.length() / 252);
		String[][] packetArray = new String[num_segments][3];
		int div = 0;
		for (int x = 1; x <= num_segments; x++) 
		{
			div = (x * 252);
			//This divides input data into data segments each of 252 bytes
			String msgSegment = paddedData.substring(div - 252, div);
			//This initializes the sequence counter to 0 and increases by 1
			t[0] = "000" + (x - 1);
			t[1] = "" + msgSegment;
			packet.add(x-1, t);            

			packetArray[x-1][0] = t[0] + t[1];
			packetArray[x-1][1] =""+ (x-1);
			packetArray[x-1][2] = t[1];

		}
		return packetArray;

	}

	/**
	 * This method calculates the hash value based on RC4-BHF techniques. It performs three operations: 
	 * appending and padding, compression and truncation
	 * @param input
	 * @param offset
	 * @return
	 */
	public String hashValue(String input, int offset) {
		System.out.println("**************************************************************************************************************************************************");
		System.out.println("Calculating hash value using three steps: Appending and Padding, Compression and Truncation");
		String result="";
		String [] res = new String[2];
		int div = (input.length()/64);
		String [] msgArray;
		msgArray = appendingAndPadding(input);

		//Display message appended and divided
		System.out.println("------- Result after Appending and padding bits ------");
		for(int x=0;x<(msgArray.length);x++)
		{
			result += "Message" + x + ": ";
			result += msgArray[x];
			result += "\n";
		} 
		System.out.println(result);

		//This performs the compression step for hash value generation
		int hstate[] = new int[256];
		hstate = compressionOperation(msgArray, offset, div);

		String cstate ="";
		//display the result for compression process step
		for (int i=0;i<256;i++)
		{
			char a = (char)hstate[i];
			cstate+=a;
		}
		System.out.println(" Result after performing compression step: \n" + cstate );
		

		//This performs the truncation step for hash value generation
		int fstate[] = new int[256];
		int ostate[] = new int[128];

		fstate = truncationProcess(hstate,cstate);

		String jstate = "";        
		for (int i=0;i<256;i++)
		{
			char j = (char)fstate[i];
			jstate += j;
		}
		//System.out.println("\n Result after performing XOR operation on last 256 byte of PRGA and last state of compression step: \n" + jstate);

		//take even bytes and select the least significant bit of each byte to produce 128-bit long hash value
		int o=0;
		for (int i=0;i<256;i++)
		{
			if((i%2)== 0)
			{
				ostate[o]=fstate[i];
				o++;
			}

		}
		
		String xstate = "";        
		for (int i=0;i<128;i++)
		{
			char j = (char)ostate[i];
			xstate += j;
		}

		//get final 128bit hash from last bit of each 256 byte XOR output
		String binary = ConvertCharToBinary(xstate);
		char [] hashValue = new char[128];
		int p=0;
		for (int i=0;i<1024;i++)
		{
			if(((i+1)%8)== 0)
			{
				hashValue[p] = binary.charAt(i);
				p++;
			}

		}

		//Display the hash value of 128 bits 
		String bin = "";        
		for (int i=0;i<128;i++)
		{
			char j = hashValue[i];
			bin += j;
		}

		//Convert binary input to character output
		String bin2char = ConvertBitStringToChar(hashValue);
		System.out.println("\n Final 16 byte of Hash  Value: \n" + bin2char + "\n");

		return bin2char;

	}

	/**
	 * This method is the last step in generating hash value. The input to this step is the last state from compression step
	 * Apply PRGA to generate 512 bytes. Perform XOR operation on Staten and last 256 bytes of 512 bytes PRGA data.
	 * @param hstate
	 * @param cstate
	 * @return
	 */
	public int[] truncationProcess(int[] hstate, String cstate) {

		int tstate[] = new int[256];
		int pstate[] = new int[512];
		int fstate[] = new int[256];
		int i=0;
		int j=0;       

		//performing KSA and PRGA round for Output Stage
		ksa(tstate,cstate,256);
		prga(tstate,pstate,512,i,j);

		//performing xor operation on the last state of compression step and last 256 bytes of PRGA 
		for(int x=0;x<256;x++)
		{
			fstate[x] = hstate[x] ^ pstate[x+256];
		}
		return fstate;
	}

	/**
	 * This method performs the compression operation for producing the hash values. 
	 * It uses ksa, prga* and ksa* operations to permutate and update the initial state S
	 * @param msgArray
	 * @param offset
	 * @param div 
	 * @return
	 */
	public int[] compressionOperation(String[] msgArray, int offset, int div) {

		int hstate[] = new int[256];
		ksa(hstate,msgArray[0],64);
		prgaStar(hstate,offset);
		prgaStar(hstate,len_offset(msgArray[0],offset));

		if (div > 1)   
			for (int x = 1; x < div; x++) 
			{            
				ksaStar(hstate,msgArray[x]);
				prgaStar(hstate,len_offset(msgArray[0],offset));
			}      

		return hstate;
	}

	/**
	 * This method performs appending and padding of 1's and 0's operation to the input packet
	 * @param input
	 * @return
	 */
	private String[] appendingAndPadding(String input) {
		int div = (input.length()/64);
		int d= 0;
		String [] msgArray = new String[div + 1];
		String paddedMsg ="";

		//Appending Process: appends 1 followed by as many 0's as necessary       
		String bit= "10000000";
		char paddedData = ConvertBinaryToChar(bit);                
		paddedMsg+=paddedData;

		//append remaining 0 bits
		for (int i = 0; i <= 60 ; i++) 
		{
			String bits= "00000000";
			char paddedchars = ConvertBinaryToChar(bits);             
			paddedMsg+=paddedchars;                
		}

		//The dividing process to divide each packets into messages of 64-bytes data
		for (int x = 1; x <= div; x++) 
		{
			d = (x * 64);
			msgArray[x-1] = input.substring(d - 64, d);  

		}

		paddedMsg+="ÿ";
		msgArray[div]= paddedMsg;
		return msgArray;

	}

	/**
	 * this method converts the character array input to string output
	 * @param input
	 * @return
	 */
	public String ConvertBitStringToChar(char[] input) {

		char[] chars = new char[input.length / 8];
		String s="";
		for (int i = 0; i < chars.length; ++i) {
			String c = "";            
			for (int j = i * 8; j < (i + 1) * 8; ++j) {
				c += input[j];
			}
			s+=ConvertBinaryToChar(c);
		}

		return s;

	}

	/**
	 * This method converts character input into binary output
	 * @param input
	 * @return
	 */
	public String ConvertCharToBinary(String input) {

		ArrayList<Integer> output = new ArrayList<>();
		String result="";

		for (char c : input.toCharArray()) {
			output.add((int) c);
			String intString = String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) c)));
			result+=intString;
		}
		return result;

	}

	/**
	 * This method performs PRGA algorithm to generate keystream to XOR with input plaintext by using permutation on internal state S
	 * @param state
	 * @param prgaState
	 * @param len
	 * @param i
	 * @param j
	 * @return
	 */
	public int [] prga(int[] state, int[] prgaState, int len, int i, int j) {

		//Generation loop         
		for (int x= 0;x<len;x++)
		{
			i = (i + 1) % 256;
			j = (j + state[i]) % 256;

			//swapping
			int temp = state[j];
			state[j] = state[i];
			state[i] = temp;
			//produces keystream based on 256 bytes internal state permutation
			prgaState[x]= state[(state[i] + state[j])% 256];
		}

		return prgaState;

	}

	/**
	 * This method ksa* operation to generate hash value in RC4-BHF
	 * @param state
	 * @param msg
	 */
	public void ksaStar(int[] state, String inputMsg) {

		int i, j = 0;
		int msg[] = new int[inputMsg.length()];
		for (i = 0; i < inputMsg.length(); i++) {
			msg[i] = (int) inputMsg.charAt(i);
		}

		for (i = 0; i < 256; i++) {
			j = (j + state[i] + msg[i % 64]) % 256;
			//Swapping operation is performed on the internal state
			int temp = state[j];
			state[j] = state[i];
			state[i] = temp;
		}     

	}

	/**
	 * This method determines the length which controls the number of times PRGA* is executed
	 * @param msg
	 * @param offset
	 * @return
	 */
	public int len_offset(String msg, int offset) {

		int lenk=0;
		int m[] = ConvertStringToInt(msg);        

		for (int x=0; x<msg.length();x++)
		{
			lenk+=lenk+m[x];
		}
		if((lenk % 256)== 0)
		{
			lenk=offset;
		}
		else
			lenk= lenk%256;

		return lenk;

	}

	/**
	 * This method converts the string input to integer output
	 * @param msg
	 * @return
	 */
	public int[] ConvertStringToInt(String msg) {

		int m[] = new int[msg.length()];
		for (int i = 0; i < msg.length(); i++) 
		{
			m[i] = (int) msg.charAt(i);
		}
		return m;

	}

	/**
	 * This method performs PRGA* opertion to generate hash value for RC4-BHF
	 * @param state
	 * @param len
	 */
	public void prgaStar(int[] state, int len) {

		int i = 0;
		int j = 0;
		//this loop is controlled by len value        
		for (int x= 0;x<len;x++)
		{
			j = (j + state[i]) % 256;            

			//swapping operation is performed
			int temp = state[j];
			state[j] = state[i];
			state[i] = temp;
		}       


	}


	/**
	 * This method performs key scheduling algorithm to initialize the internal states based on input message
	 * @param state
	 * @param msg
	 * @param keylen
	 */
	public void ksa(int[] state, String inputMsg, int keylen) {
		//two 1-byte indexes
		int i, j = 0;
		int t[] = new int[inputMsg.length()];
		// initializing the internal state from 0 to 255
		for (i = 0; i < 256; i++) {
			state[i] = i;
		}
		for (i = 0; i < inputMsg.length(); i++) {
			t[i] = (int) inputMsg.charAt(i);
		}
		// performing KSA
		for (i = 0; i < 256; i++) {
			j = (j + state[i] + t[i % keylen]) % 256;
			int temp = state[j];
			state[j] = state[i];
			state[i] = temp;
		}

	}

	/**
	 * This method performs the encryption operation on the message which contains data segments and hash value
	 * @param message
	 * @param key
	 * @return
	 */
	public String encryptionOperation(String message, String key) {

		int a = 0;
		int b = 0;
		String cipher="";
		int [] state = new int[256];
		int [] prgaState = new int[message.length()];

		//performing KSA and PRGA round to generate cipher text
		ksa(state,key,key.length());
		prga(state,prgaState,message.length(),a,b);
		cipher = xor(message,prgaState);
		return cipher;

	}

	/**
	 * This method performs the XOR operation on the message and prga state
	 * @param msg
	 * @param prgastate
	 * @return
	 */
	public String xor(String inputMsg, int[] prgastate) {

		int msg[] = new int[inputMsg.length()];
		char [] result = new char[inputMsg.length()];
		String cipher="";

		for (int i = 0; i < inputMsg.length(); i++) {
			msg[i] = (int) inputMsg.charAt(i);
		}
		for (int i = 0; i < inputMsg.length(); i++) {
			result[i] = (char)(prgastate[i]^msg[i]);
			cipher+=result[i];
		}
		return cipher;

	}

}