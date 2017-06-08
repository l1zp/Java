package Tetris;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
import javax.swing.border.*;

public class background {
	static int[][] pattern = {	{ 0x0f00, 0x4444, 0x0f00, 0x4444 }, 
								{ 0x04e0, 0x0464, 0x00e4, 0x04c4 }, 
								{ 0x4620, 0x6c00, 0x4620, 0x6c00 }, 
								{ 0x2640, 0xc600, 0x2640, 0xc600 }, 
								{ 0x6220, 0x1700, 0x2230, 0x0740 }, 
								{ 0x6440, 0x0e20, 0x44c0, 0x8e00 }, 
								{ 0x0660, 0x0660, 0x0660, 0x0660 }};
	
	JFrame frame = new JFrame("test");
	JPanel panel = new JPanel();
	
	Dead dead1 = new Dead();
	controlPanel con = new controlPanel();
	boolean bool_fast = false;
	
	int x, y;
	int px, py;
	int style;
	int speed = 500, speed_fast = 100000;
	
	int[][] condition = new int[11][25];
	//int[][] dead = dead1.dead;
	Color color;
	JLabel[][] label = new JLabel[11][25];
	//���캯��

	//��ʼ��block
	void initBackground(block blo, block blo_next){
		x = blo.x;
		y = blo.y;
		px = blo.px;
		py = blo.py;
		color = blo.color;
		style = pattern[px][py];
		con.getNext(blo_next);
	}
	//���Ƶײ㷽��
	void go(){
		frame.setSize(600, 630);
		JPanel mainpanel = (JPanel)frame.getContentPane();
		for(int j = 0; j < 25; j ++){
			for(int i = 0; i < 11; i ++)
				label[i][j] = new JLabel();
		}
		
		for(int j = 0;j < 20;j ++){
			for(int i = 1;i < 11;i ++){
				label[i][j] = new JLabel();
				//label[i][j].setBackground(Color.BLACK);
				label[i][j].setOpaque(true);
				label[i][j].setBackground(Color.WHITE);
				label[i][j].setBorder(new LineBorder(Color.BLACK));
				panel.add(label[i][j]);	
			}
		}
		//�����·��߽�
		for(int i = 1;i < 11; i++){
			label[i][20] = new JLabel();
			label[i][20].setOpaque(true);
			label[i][20].setBackground(Color.BLACK);
			dead1.dead[i][20] = 1;
			panel.add(label[i][20]);	
		}
		//�������߽�
		for(int j = 0;j < 20; j ++){
			label[1][j].setBackground(Color.BLACK);
			dead1.dead[1][j] = 1;
		}
		//�����Ҳ�߽�
		for(int j = 0;j < 20; j ++){
			label[10][j].setBackground(Color.BLACK);
			dead1.dead[10][j] = 1;
		}

		panel.setLayout(new GridLayout(21,10));
		

		mainpanel.setLayout(new GridLayout(1, 2));
		
		mainpanel.add(con);
		mainpanel.add(panel);
		
		frame.setVisible(true);
		changeFocus();

		//frame.addKeyListener(this);
		panel.addKeyListener(new KeyListener()
				{
					public void keyReleased(KeyEvent e){
						if(e.getKeyCode() == KeyEvent.VK_DOWN){
							bool_fast = false;
						}
					 }
					public void keyTyped(KeyEvent e){
					}
					public void keyPressed(KeyEvent e){
						if(e.getKeyCode() == KeyEvent.VK_LEFT){
							moveLeft();
						}
						if(e.getKeyCode() == KeyEvent.VK_RIGHT){
							moveRight();
						}
						if(e.getKeyCode() == KeyEvent.VK_SPACE){
							changeStyle();
						}
						if(e.getKeyCode() == KeyEvent.VK_DOWN){
							speed_fast = 100;
							bool_fast = true;
						}
					}
				});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//���Ʒ���
	void getBlock(){
		
		changeFocus();
		int num = style;//����style
		for(int j = y + 3;j > y - 1; j --){
			for(int i = x + 3;i > x - 1; i --){
				if(dead1.dead[i][j] == 0)//�ж��Ƿ�dead
					condition[i][j] = num & 1;
				num >>= 1;//����style����	
				if(condition[i][j] == 1)
					label[i][j].setBackground(color);
			}
		}
	}
	
	void moveDown(){
		while(y < 20){//����Сbugע���޸�
			//�ж��Ƿ�Ӧ��ֹͣ
			for(int j = y + 3;y - 1 < j ;j --){
				for(int i = x;i < x + 4;i ++){
					if((dead1.dead[i][j + 1] & condition[i][j]) == 1){
						dead1.writeDead(this);
						refresh();
						return;
						}
				}
			}
			while(con.bool_PAUSE){
				//con.bool_PAUSE = con.refresh();
				System.out.println();
			}
			clear();
			y ++;
			getBlock();

			speed = 100 * (11 - con.level);

			if(bool_fast == false){
				try{Thread.sleep(speed);} catch(Exception e){}
			}
			else if(bool_fast == true){
				try{Thread.sleep(speed_fast);} catch(Exception e){}
			}
		}
		dead1.writeDead(this);
		refresh();
	}
	
	
	
	void moveLeft(){
		if(x < 1)
			return;
		for(int i = x ;i < x + 4 ;i ++){
			for(int j = y;j < y + 4;j ++){
				if((dead1.dead[i - 1][j] & condition[i][j]) == 1)
					return;
			}
		}
		//����
		clear();
		x --;
		getBlock();
	}
	//����
	void moveRight(){
		if(x > 10)
			return;
		for(int i = x + 3;x - 1 < i ;i --){
			for(int j = y;j < y + 4;j ++){
				if((dead1.dead[i + 1][j] & condition[i][j]) == 1)
					return;
			}
		}

		clear();
		x ++;
		getBlock();		
	}
	void changeStyle(){
		py += 1;
		style = pattern[px][py % 4];
		//���ԭ��ͼ��
		clear();
		getBlock();
		
	}

	void clear(){
		int num = 0;
		for(int j = y;j < y + 4;j ++){
			for(int i = x;i < x + 4;i ++){
				condition[i][j] = num;
				if(dead1.dead[i][j] == 0)
					label[i][j].setBackground(Color.WHITE);
			}
		}
	}
	
	void refresh(){
		int score = dead1.clearLine();
		con.getScores(score);
		for(int j = 0; j < 20;j ++){
			for(int i = 2;i < 10;i ++){
				if(dead1.dead[i][j] == 1)
					label[i][j].setBackground(dead1.colors[i][j]);
				else if(dead1.dead[i][j] == 0)
					label[i][j].setBackground(Color.WHITE);
			}
		}
	}
	
	void changeFocus(){
		frame.requestFocus();
		con.requestFocus(false);
		panel.requestFocus();		
	}
}
