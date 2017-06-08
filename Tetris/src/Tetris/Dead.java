package Tetris;

import java.awt.*;

public class Dead {
	int[][] dead = new int[20][25];
	Color[][] colors = new Color [20][25];
	
	int score = 0;
	//判断是否应该消除
	int clearLine(){
		int sum = 0;
		int mutiple = 1;
		int i, j;
		for(j = 19;0 < j;j --){
			sum = 0;
			for(i = 2;i < 10;i ++){
				sum += dead[i][j];
			}
			if(sum == 8){
				//dead下移
				for(int a = j;0 < a;a --){
					for(int b = 2;b < 10;b ++){
						dead[b][a] = dead[b][a - 1];
						colors[b][a] = colors[b][a - 1];
						
					}
				}
				//System.out.println("dead move down!");
				j ++;
				mutiple *= 2;
				score += 10 * mutiple;
			}
		}
		return score;
	}
	
	void writeDead(background b){
		for(int j = b.y + 3; b.y - 1 < j ; j --){
			for(int i = b.x; i < b.x + 4;i ++){
				dead[i][j] |= b.condition[i][j];
				if(b.condition[i][j] == 1)
					colors[i][j] = b.color;
				b.condition[i][j] = 0;				
				//System.out.println(dead[i][j]);			
			}
		}
	}
	
	boolean quitGame(){
		for(int i = 2; i < 10; i ++){
			if(dead[i][2] == 1)
				return false;
		}
		return true;
	}
}
