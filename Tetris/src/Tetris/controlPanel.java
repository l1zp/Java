package Tetris;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class controlPanel extends JPanel {
	static int[][] pattern = {	{ 0x0f00, 0x4444, 0x0f00, 0x4444 }, 
			{ 0x04e0, 0x0464, 0x00e4, 0x04c4 }, 
			{ 0x4620, 0x6c00, 0x4620, 0x6c00 }, 
			{ 0x2640, 0xc600, 0x2640, 0xc600 }, 
			{ 0x6220, 0x1700, 0x2230, 0x0740 }, 
			{ 0x6440, 0x0e20, 0x44c0, 0x8e00 }, 
			{ 0x0660, 0x0660, 0x0660, 0x0660 }};
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	
	int level = 6;
	String s = "" + level;
	boolean bool_PAUSE, bool_BEGIN = false;
	JLabel[][] labels = new JLabel[6][6];
	
	JLabel label_scores ;
	
	controlPanel(){
		
		getPanel1();
		getPanel2();
		getPanel3();
		getPanel4();
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		
		this.setLayout(null);
		this.setVisible(true);
	}
	void getPanel1(){
		panel1.setBounds(0, 0, 300, 200);
		
		JPanel p = new JPanel();
		p.setBounds(60, 20, 180, 180);
		
		for(int i = 0; i < 6; i ++){
			for(int j = 0; j < 6; j ++){
				
				labels[i][j] = new JLabel();
				labels[i][j].setOpaque(true);
				labels[i][j].setBackground(Color.WHITE);
				labels[i][j].setBorder(new LineBorder(Color.BLACK));
				p.add(labels[i][j]);
			}
		}
		panel1.add(p);
		panel1.setLayout(null);
		panel1.setVisible(true);
		p.setLayout(new GridLayout(6, 6));
	}
	void getPanel2(){
		JLabel label1 = new JLabel("level");
		JButton button1 = new JButton("+");
		JLabel label2 = new JLabel(s);
		JButton button2 = new JButton("-");

		button1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						System.out.println("+");
						level ++;
						if(level > 10)
							level --;
						s = "" + level;
						label2.setText(s);
					}
				});
		
		button2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						System.out.println("-");
						level --;
						if(level < 0)
							level ++;
						s = "" + level;
						label2.setText(s);
					}
				});
		panel2.add(label1);
		panel2.add(button1);
		panel2.add(label2);
		panel2.add(button2);
		panel2.setBounds(0, 250, 300, 100);
		panel2.setLayout(new FlowLayout());
	}
	void getPanel3(){
		String s = "" + 0;
		JLabel label = new JLabel("score");
		label_scores = new JLabel(s);
		
		panel3.add(label);
		panel3.add(label_scores);
		panel3.setBounds(0, 350, 300, 100);
	}
	void getPanel4(){
		JButton button1 = new JButton("PAUSE");
		JButton button2 = new JButton("BEGIN");
		
		button1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						if(!bool_PAUSE){
						bool_PAUSE = true;
						button1.setText("START");
						}
						else{
							bool_PAUSE = false;
							button1.setText("PAUSE");
						}
					}
				});
		button2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						if(!bool_BEGIN){
							bool_BEGIN = true;
							button2.setText("QUIT");
							}
							else{
								bool_BEGIN = false;
								button2.setText("BEGIN");
							}
					}
				});
		
		panel4.add(button1);
		panel4.add(button2);
		panel4.setBounds(0, 450, 300 ,100);
		panel4.setLayout(new FlowLayout());
	}
	public void getScores(int scores){
		String s = "" + scores;
		label_scores.setText(s);
	}
	public void getNext(block blo){
		int num = pattern[blo.px][blo.py];
		System.out.println("Ê®Áù½øÖÆ:" + Integer.toHexString(num));    
		System.out.println("px" + blo.px + "py" +blo.py);
		//clear
		
		for(int j = 0; j < 6; j ++){
			for(int i =0; i < 6; i ++)
				labels[i][j].setBackground(Color.WHITE);
		}
		Color color = new Color(0, 255, 255);
		for(int j = 4; j > 0; j --){
			for(int i = 4; i > 0; i --){				
				if((num & 1) == 1)
					labels[j][i].setBackground(blo.color);
				num >>= 1;
			}
		}

	}
}
