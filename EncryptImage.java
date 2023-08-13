import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EncryptImage {
	public static void main(String args[]) throws IOException {

		File file = new File("rose32.jpg"); // I have bear.jpg in my working directory
		//FileInputStream fis = new FileInputStream(file);
		BufferedImage image = ImageIO.read(file); //reading the image file
		int width = image.getWidth();
		int height = image.getHeight();
		System.out.println(width);
		System.out.println(height);

		int[][] imgArr = new int[width][height];
		Raster raster = image.getData();
		//Reading the input image in bmp format into imgArr[][]
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				imgArr[j][i] = raster.getSample(j, i, 0);
			}
		}
		System.out.println("2D array last element ---> " + imgArr[width-1][height-1]);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("matrix.txt"));
			for (int i = 0; i < imgArr[0].length; i++) {
				for (int j = 0; j < imgArr.length; j++) {
					bw.write(imgArr[j][i] + " ");
				}
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int[] oneDArray = new int[width * height];
		for(int i = 0; i < height; i ++)
		{
			for(int s = 0; s < width; s ++)
			{
				oneDArray[(i * width) + s] = imgArr[s][i];
			}
		}

		int nBlocks = (width*height*8)/64;
		System.out.println(nBlocks);
		int inputBits[] = new int[64];

		for(int i=0 ; i < width*height ; i=i++) {
			String s = Integer.toBinaryString(oneDArray[i]);

			while(s.length() < 8) {
				s = "0" + s;
			}
		//	System.out.println(s);
			for(int k=0;k<8; k++) {
				for(int j=0 ; j < 8 ; j++) {
					int a = Integer.parseInt(s.charAt(j)+"");
					inputBits[(8*k)+j] = Integer.parseInt(s.charAt(j) + "");
				}
			}
			
			
		}
		
		

	}

}
