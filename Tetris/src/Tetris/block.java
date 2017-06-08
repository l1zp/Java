package Tetris;

import java.awt.Color;
import java.util.*;

public class block {
	Color purple = new Color(255, 0, 255);
	Color[] colors = {	Color.red, Color.green, Color.blue, purple,
						Color.orange, Color.yellow, Color.cyan};
	
	int[][] position = new int[11][25];//���ö�Ӧ��GUI��λ��״̬����
	
	
	int x, y;//����block��ʼλ��
	int style;//��������
	
	int px, py;//��һ���Ƿ���࣬����ɾ����

	int seed = 0;//�����ʼ������
	Color color;
	block(){
		initCoordinate();
		initBlock();
		initColor();
		
	}
	//�����������
	void seed(){
		final int max = 100; // ����������[0, max)֮�����
        Random rand = new Random(System.currentTimeMillis());
            int r = (rand.nextInt(max - 1) + 1 + seed) % max;
            seed = r;
	}
	
	//��ʼ������
	void initCoordinate(){
		x = 5;
	}
	
	//��ʼ��block����
	void initBlock(){
		seed();

		Random a = new Random(seed);
		px = Math.abs(a.nextInt(7));
		Random b = new Random(seed);
		py = Math.abs(b.nextInt(4));
	}
	
	//��ʼ��block��ɫ
	Color initColor(){

		return color = colors[px];
	}
	
}
