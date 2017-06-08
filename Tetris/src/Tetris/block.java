package Tetris;

import java.awt.Color;
import java.util.*;

public class block {
	Color purple = new Color(255, 0, 255);
	Color[] colors = {	Color.red, Color.green, Color.blue, purple,
						Color.orange, Color.yellow, Color.cyan};
	
	int[][] position = new int[11][25];//设置对应于GUI的位置状态数组
	
	
	int x, y;//设置block起始位置
	int style;//设置类型
	
	int px, py;//这一句是否多余，考虑删除啊

	int seed = 0;//随机初始化种子
	Color color;
	block(){
		initCoordinate();
		initBlock();
		initColor();
		
	}
	//构造随机种子
	void seed(){
		final int max = 100; // 用来产生在[0, max)之间的数
        Random rand = new Random(System.currentTimeMillis());
            int r = (rand.nextInt(max - 1) + 1 + seed) % max;
            seed = r;
	}
	
	//初始化坐标
	void initCoordinate(){
		x = 5;
	}
	
	//初始化block类型
	void initBlock(){
		seed();

		Random a = new Random(seed);
		px = Math.abs(a.nextInt(7));
		Random b = new Random(seed);
		py = Math.abs(b.nextInt(4));
	}
	
	//初始化block颜色
	Color initColor(){

		return color = colors[px];
	}
	
}
