package me.ruyili;

import java.io.*;
import java.net.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import java.awt.event.*;

public class WebToText {
	
	static final JFrame f = new JFrame("Website to txt");
	public static void main(String[] args){
		
		JPanel p = new JPanel();
		
		final JTextField website = new JTextField(20);
		final JTextField out = new JTextField(13);
		
		JButton convert = new JButton("Convert");
		out.setText("Output txt directory");
		JButton output = new JButton("Browse...");
		final String[] s = {"/"};
		out.setEditable(false);
		
		f.setSize(300, 125);
		f.setResizable(false);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(p);
		p.add(website);
		p.add(out);
		p.add(output);
		p.add(convert);
		f.revalidate();

		output.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc1 = new JFileChooser();
	            jfc1.setDialogTitle("Output as...");
	            jfc1.setFileFilter(new FileNameExtensionFilter("Text Document(.txt)", "txt"));
	            jfc1.setAcceptAllFileFilterUsed(false);
	            
	            if (jfc1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	                out.setText("" + jfc1.getSelectedFile());
	                s[0] = out.getText();
	            }
			}
			
		});
		
		convert.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(website.getText() == ""){
					JOptionPane.showMessageDialog(f, "You didn't specify the URL!");
				}else if(s[0] == "/"){
					JOptionPane.showMessageDialog(f, "You didn't specify the output file!");
				}else{
					try {
						urlToTxt(website.getText(), s[0] + ".txt");
						JOptionPane.showMessageDialog(f, "Sucessfully converted!");
					} catch (IOException|ResponseException e1) {
						JOptionPane.showMessageDialog(f, "Invalid URL!");
						
					}
				}
			}
			
		});
	}
	
	
	
	
	
	
	

	public static void urlToTxt(String from, String to) throws IOException, ResponseException{
		
		File file = new File(to);
		UserAgent userAgent = new UserAgent();                       
		userAgent.visit(from);
		
		FileWriter fw = new FileWriter(file);
		fw.write(userAgent.doc.innerHTML());
		fw.flush();
		fw.close();
		
	}

}
	
	
