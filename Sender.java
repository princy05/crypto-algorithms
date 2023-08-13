


//import RC4project.Output;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olamide.olafamoye
 */
public class Sender extends javax.swing.JFrame {

    /**
     * Creates new form Sender
     */
    public Sender() {
        initComponents();
    }
    String[][] packet;
    String [][] encrypt_packet;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        input_message = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        s_input_key = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inp_offset = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        decrypt = new javax.swing.JButton();
        r_input_offset = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        r_input_key = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        s_order = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        compromise = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sender Interface");

        jLabel1.setText("Input the plain text message:");

        input_message.setColumns(20);
        input_message.setLineWrap(true);
        input_message.setRows(5);
        input_message.setWrapStyleWord(true);
        jScrollPane1.setViewportView(input_message);

        jLabel2.setText("Input the key value:");

        jLabel3.setText("Input the offset number:");

        jButton1.setText("Send Message");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encrypt(evt);
            }
        });

        decrypt.setText("Receive Message");
        decrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptActionPerformed(evt);
            }
        });

        jLabel4.setText("Input the key value:");

        jLabel5.setText("Input the offset number:");

        r_input_key.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_input_keyActionPerformed(evt);
            }
        });

        jLabel6.setText("Sender Input Interface");

        jLabel7.setText("Receiver Input Interface");

        jLabel8.setText("Packet Sending Order");

        jLabel9.setText("Enter 0: To send packet in the right order ie 0,1,2,3");

        jLabel10.setText("Enter 1: To send 2nd packet first 1,0,2,3");

        jLabel11.setText("Enter 2: To send packet in reverse order 3,2,1,0");

        compromise.setText("Compromise?");
        compromise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compromiseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(31, 31, 31)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(s_input_key)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(inp_offset, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE)))))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(decrypt))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(r_input_key)
                                    .addComponent(r_input_offset, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(compromise)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(31, 31, 31)
                                .addComponent(s_order, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addComponent(s_input_key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(r_input_key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(r_input_offset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(decrypt)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inp_offset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(s_order, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(compromise)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Call the class with all the methods.
    RC4project run = new RC4project();
    String rkey;
    String skey;
    int roffset;
    int soffset;
    int order;
    int pad;
    String plaintext;
    String [] shashpacket;
    
        
    private void encrypt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encrypt
        // TODO add your handling code here:
        //Call Class Output frame
        Output aFrame = new Output();   
        
        //get text from TextBox
        plaintext = input_message.getText();
        
        soffset = Integer.parseInt(inp_offset.getText());
        skey = s_input_key.getText();
        order = Integer.parseInt(s_order.getText());
        
        //Output in the output frame  
        aFrame.result.append("Inputed Text By Sender \n");
        aFrame.result.append(plaintext);
        aFrame.result.append("\nThere are " + plaintext.length() +" bytes of data");
        
        
        //Call method getPacketsthat and show the appended bytes   
        
        String app_plaintext = run.getPackets(plaintext);
        aFrame.result.append("\n \n");
        aFrame.result.append("Text After Initial Padding \n");
        aFrame.result.append(app_plaintext);
        aFrame.result.append("\n 'There are " + app_plaintext.length()+" bytes of data' \n\n");
        
        //Call method packets that returns array with the entire packetc including Sequence counter, data segment and hash value
        packet = run.packets(app_plaintext);
        System.out.println("packet length--->" + packet.length);
        // Put Array element in string to output in frame
        String outpacket = "Result of Packet after appending SC to message \n";
        String outhpacket="";
        String encrypted_data="";        
        String encrypted_hash="";        
        encrypt_packet = new String[packet.length][2];
        
        for (int x = 0; x <= (packet.length - 1); x++) 
        {  
            //save sequence counter in encrypted packet array
            encrypt_packet[x][0]= packet[x][1];
            //output inputted message
            String a = packet[x][0];           
            outpacket += a + " --- 'The size is "+ a.length()+ " bytes'\n";
            //Output hash appending process
            shashpacket = run.HashValue(a,soffset);
            
            //EncryptData and Hash segment
            encrypted_data = run.EncryptDataSegment(packet[x][2]+shashpacket[1],skey);            
            encrypt_packet[x][1] = encrypted_data;
            
            //Get sending order
            
            
            outhpacket+=shashpacket[0]; 
            outhpacket+="\nEncrypted message -- "+encrypted_data+"\n";         
            //outhpacket+="\nEncrypted Hash -- "+encrypted_hash+"\n";      
        }           
              
        //Show has value dividing step
        //output in frame
        aFrame.result.append(outpacket);
        aFrame.result.append(outhpacket);
        aFrame.setVisible(true);
    }//GEN-LAST:event_encrypt

    //method used to modify hashvalue of sender.
    public String compromise(String shash)
    {
        
        shash="tt"+shash;
        return shash;
        
    }
    public boolean comparehash(String shash,String rhash)
    {
        
        if (shash.equals(rhash))
            return true;
        else
            return false;
        
    }   
    //checks if the hash value has been compromised by calculating hash and comparing with senders hash
    public String[] checkCompromise(String dec,int y)
    {    
        String shash = dec.substring(252);
        String dplain = dec.substring(0,252);
        String [] rhash = run.HashValue("000"+y+dplain,soffset);
        String [] res=new String[3];
        if (comp=="true")
        {
            shash=compromise(shash);
        }
        //res[0]="<<Received Hash>> "+ dec+ "<</Received Hash>>\n\n";
        res[0]="\n<< Calculated Hash>> "+ rhash[1]+ "<<Calculated Hash>>";
        

        boolean stat = comparehash(shash,rhash[1]);                    
        if (stat==true)
        {
            res[0]+="<<Received Hash>> "+ shash+ "<</Received Hash>>\n";
            //res[1]="\n<<SC>>"+y+ "<</SC>><<Decrypted Data+Hash>>"+ dec+"<</Decrypted Data+Hash>>\n";
            res[2]= "true";
        }
        else
        {
            //res[0]+="<<compromised hash>> "+ shash+ "<</compromised hash>>\n\n";
            res[0]+="<<Received Hash>> "+ shash+ "<</Received Hash>>\n";
            res[1]= "\nMessage has been compromised. Request Sender to Resend\n";
            res[2] = "false";
        }
        return res;
    }
    String comp="";
    private void decryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptActionPerformed
        // TODO add your handling code here:
        //Call Class Output frame
        Output aFrame = new Output();
        String result ="";
        String [] hashpacket;

        roffset = Integer.parseInt(r_input_offset.getText());

        rkey = r_input_key.getText();
        String temp= "";
        String temp2= "";
        String temp3= "";
        boolean stat= false;
        String [] res = new String[3];
        if ((rkey.equals(skey)) && (roffset == soffset))
        {
            
            
            if(order==0)
            {
                result +="Packets Recieved from sender with Key = " +rkey+" Offset="+roffset;
                for (int x = 0; x < encrypt_packet.length; x++)
                {
                    
                    temp3+=x+",";
                    
                    String dec = run.EncryptDataSegment(encrypt_packet[x][1],rkey);
                    res = checkCompromise(dec,x);
                    
                    if(res[2].equals("false"))
                    {
                        result+=res[0]+res[1];
                        break;
                        
                    }   
                    result+="<<SC>>"+ encrypt_packet[x][0] + "<</SC>>\n";
                    result+="<<Encrypted Data+Hash>> "+ encrypt_packet[x][1]+ "<</Encrypted Data+Hash>>\n";
                    result+=res[0];
                }                
                temp3="packet received in order"+temp3+"\n";
            }
            else if(order==1)
            {
                result +="Packets Recieved from sender with Key = " +rkey+" Offset="+roffset;
                String c="";                
                
                for (int x = 1; x >= 0; x--)
                {
                    temp3+=x+",";
                    String dec = run.EncryptDataSegment(encrypt_packet[x][1],rkey);
                    res = checkCompromise(dec,x);
                    
                    if(res[2].equals("false"))
                    {
                        result+=res[0]+res[1];
                        c="false";
                        break;
                        
                    }
                    result+="\n<<SC>>"+ encrypt_packet[x][0] + "<</SC>>\n";
                    result+="<<Encrypted Data+Hash>> "+ encrypt_packet[x][1]+ "<</Encrypted Data+Hash>>\n";
                    result+=res[0];
                    
                    

                }
                for (int x = 2; x < encrypt_packet.length; x++)
                {
                    temp3+=x+",";
                    if(c.equals("false"))
                    {                        
                        break;                        
                    }
                    result+="\n<<SC>>"+ encrypt_packet[x][0] + "<</SC>>\n";
                    result+="<<Encrypted Data+Hash>> "+ encrypt_packet[x][1]+ "<</Encrypted Data+Hash>>\n";
                    String dec = run.EncryptDataSegment(encrypt_packet[x][1],rkey);
                    res = checkCompromise(dec,x); 
                    result+=res[0];
                    
                    //temp+=res[1];
                }               
                //temp3="packet received in order"+temp3+"\n";
            }
            else if(order==2)
            {
                result +="Packets Recieved from sender with Key = " +rkey+" Offset="+roffset;
                for (int x =(encrypt_packet.length-1); x >=0 ; x--)
                {
                    temp3+=x+",";
                    String dec = run.EncryptDataSegment(encrypt_packet[x][1],rkey);
                    res = checkCompromise(dec,x);
                    temp3+=res[0];
                    if(res[2].equals("false"))
                    {
                        result+=res[0]+res[1];
                        break;
                        
                    }
                    result+="\n<<SC>>"+ encrypt_packet[x][0] + "<</SC>>\n";
                    result+="<<Encrypted Data+Hash>> "+ encrypt_packet[x][1]+ "<</Encrypted Data+Hash>>\n";
                    result+=res[0];
                    //temp+=res[1];
                }                
                temp3="packet received in order"+temp3+"\n";
            }
            else
            {
                result +="Packets Recieved from sender with Key = " +rkey+" Offset="+roffset;
                for (int x = 0; x < encrypt_packet.length; x++)
                {
                    
                    String dec = run.EncryptDataSegment(encrypt_packet[x][1],rkey);
                    res = checkCompromise(dec,x);
                    if(res[2].equals("false"))
                    {
                        result+=res[0]+res[1];
                        break;
                        
                    }                                
                    result+="\n<<SC>>"+ encrypt_packet[x][0] + "<</SC>>\n";
                    result+="<<Encrypted Data+Hash>> "+ encrypt_packet[x][1]+ "<</Encrypted Data+Hash>>\n";                        
                     
                    result+=res[0];
                    //temp+=res[1];
                }                
                
            }
            
            if (res[2]=="true")
            {
                temp+="\n\nMessage \n";
                for (int x = 0; x < encrypt_packet.length; x++)
                    {
                        String t = run.EncryptDataSegment(encrypt_packet[x][1],rkey);
                        temp2+=t.substring(0,252);

                    }
                temp+=temp2.substring(0,plaintext.length());
            }
            //temp+=temp2.length();
        }
        else
            result+="There is no message with Key = " +rkey+" Offset="+roffset;
        
        aFrame.result.append(temp3);
        aFrame.result.append(result);
        aFrame.result.append(temp);
        
        
        aFrame.setVisible(true);
    }//GEN-LAST:event_decryptActionPerformed

    private void r_input_keyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_input_keyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_input_keyActionPerformed

    private void compromiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compromiseActionPerformed
        // TODO add your handling code here:
        
        if (compromise.isSelected()) {
 
            comp= "true";

        } else {
            comp="false";}
    }//GEN-LAST:event_compromiseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sender().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox compromise;
    private javax.swing.JButton decrypt;
    public javax.swing.JTextField inp_offset;
    private javax.swing.JTextArea input_message;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField r_input_key;
    private javax.swing.JTextField r_input_offset;
    private javax.swing.JTextField s_input_key;
    public javax.swing.JTextField s_order;
    // End of variables declaration//GEN-END:variables
}
