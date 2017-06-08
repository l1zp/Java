package Tetris;

import javax.swing.*;

public class final_test2 {
	public static void main(String[] args){
		background back = new background(); 
		back.go();
		do{
			System.out.println();
		}while(!back.con.bool_BEGIN);

		block b_next = new block();
		while(back.dead1.quitGame()){
			block b = b_next;
			b_next = new block();
			
			back.initBackground(b, b_next); 		
	   		back.moveDown();
		}
		
		JFrame frame = new JFrame("Game over!");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("GAME OVER!");
		frame.add(panel);
		panel.add(label);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
}
