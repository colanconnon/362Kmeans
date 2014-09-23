import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;


public class KMeans {
	ArrayList<pixel> centroidlist = new ArrayList<pixel>();
	static ArrayList<Integer> distancelist = new ArrayList<Integer>();
	static String number;
	
	//int for the height of the image
	static int height;
	//int for the width of the image
	static int width;
	// Random number for random centroid
	static Random rand;
	// Buffered image to recreate a image
	static BufferedImage b2;
	//An array to recreate the image from the Kmeans
	static int rbgarraycreate[][];
	
	
	//An Array for holding the pixels
	static ArrayList<pixel> pixelarray = new ArrayList<pixel>();
	// A second array for holding the pixels
	static ArrayList<pixel> pixelarray1 = new ArrayList<pixel>();
	//An array of the clusters
	static ArrayList<Cluster> clusterarray = new ArrayList<Cluster>();
	//Variables used for other stuff
	static BufferedImage image; 
	static ArrayList<pixel> holderarray;
	static String imagename;

	public static void main (String[] args){
		//Get the file name and Read it in
		System.out.println("Enter the Filename to be read");
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			imagename = bufferedreader.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			image = ImageIO.read(new FileInputStream(imagename));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//Get the height and width of the image and store them into static ints for class wide access
		height = image.getHeight();
		width = image.getWidth();
	
		//prompt for how many centroids the user wants
		System.out.println("How Many centroids");
		
		
		try {
			number = bufferedreader.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//Get the ammount of centroids the user wants
		int value = Integer.parseInt(number); 
		//Read in the image to a array
		getpixels(image);
		//print the array for testing
		//printpixels();
		//Main Kmeans Function
		kmeans(value);
		//recreate the image
		createimage();
		
		
	}
	public static void getpixels(BufferedImage b1){
		
		//This function is pretty simple  I just pass in my image object and get all the pixels values from the image and store it into a 2d array
		System.out.println(b1.getHeight());
		System.out.println(b1.getWidth());
		
		for(int i=0; i<b1.getHeight(); i++){
			for(int j=0; j<b1.getWidth(); j++){
				Color c = new Color(b1.getRGB(j, i));
				int red = c.getRed();
				pixel pixel1 = new pixel();
				pixel1.setRGB(red);
				pixel1.setWidth(j);
				pixel1.setHeight(i);
				pixelarray.add(pixel1);
				
				
				System.out.println("pixel: "+ i + "," + j + "  RGB VALUE: " + red);
				
				System.out.println();
			}
			
		}
		
		
	}
	public static void printpixels(){
		// just a testing function 
		for (int i =0; i<pixelarray.size();i++){
			pixel pixel1 = new pixel();
			pixel1 = pixelarray.get(i);
			System.out.println("Height: " + pixel1.getHeight() + " Width: " + pixel1.getWidth() + " RGB Value: " + pixel1.getRGB());
		}
	}
	public static void kmeans(int k){
		//Main kmeans functiun

		//initalize a variable
		int lastavg = 0;
		for(int i = 0; i<k ; i++){
			//create the amount of clusters that the k parameter says to create
			Cluster cluster1 = new Cluster();
			//make a cluster get a random starting value and then add it to the cluster array
			cluster1.setValue(generatecentroid());
			
			clusterarray.add(cluster1);
			
			
		}
			
			boolean keepgoing = true;
			while(keepgoing){
				//main loop
				for(int i = 0; i< pixelarray.size() ; i++){
					int distance1 = 0;
					// for every pixel object in the pixel array
					
					for(int j = 0; j <k ; j++){
						// for every cluster
						
						
						int value1 = euclideandistance(clusterarray.get(j).getValue() , pixelarray.get(i).getRGB());
						//get the distance from every cluster for each pixel
						
						if (value1 < (euclideandistance(clusterarray.get(distance1).getValue() , pixelarray.get(i).getRGB())))
							{
								//if the distance is less than the lowest distance recorded set the lowest distance recorded to j
							distance1 = j;
							}
		
						}
					//each cluster has an array that contains the pixels that is closest to it
					ArrayList<pixel> array1 = clusterarray.get(distance1).getArray();
					if(array1.contains(pixelarray.get(i))){
						// do nothing if that pixel is already in that array
					}
					else{
						// add that pixel if its not in the array
						array1.add(pixelarray.get(i));
					}
					//set the array to the most current array that contains the pixels
					clusterarray.get(distance1).setArray(array1);
					// get the avg of the array
					int avg = getaverage(clusterarray.get(distance1).getArray(), i);
					// get the avg of the last time
					int preavg = clusterarray.get(distance1).getAverage();
					// set the avg of this time
					clusterarray.get(distance1).setAverage(avg);
					if(preavg == avg){
						//if avgs have converged break the loop
						keepgoing = false;
						
					}
					else{
						//keep going if hasnt converged 
						keepgoing = true;
					
					}
					
					
					
					
				}
			}
			
		for(int i =0; i<clusterarray.size();i++){
			//just printing out all the pixels and the size of each cluster array 
			System.out.println(i + " size  is " + clusterarray.get(i).getArray().size());
			System.out.println(i + " avg is " + clusterarray.get(i).getAverage());
			ArrayList<pixel> array1 = clusterarray.get(i).getArray();
			for(int j = 0;j<array1.size();j++){
				// set the avg of each value to the avg of the cluster that is in
				array1.get(j).setRGB(clusterarray.get(i).getAverage());
			}
			
		}
}
	
	private static int getaverage(ArrayList<pixel> array1, int num) {
		int avg = 0;
		//just a simple function to calc average 
		for(int i = 0; i < array1.size(); i++){
			
			avg += (array1.get(i).getRGB());
			
		}
		if(avg != 0){
			avg = avg/(array1.size());
		}
		
		
		return avg;
		
		
	}
	public static int euclideandistance(int point1,int point2)
	{
		// just a simple function to get the distance between two points
		int result = (int) Math.sqrt(Math.pow(point1 - point2, 2));
		return result;
		
	}
	public static int generatecentroid(){
		// just a simple function generate a centroid and set it to a random point 
		Random rand = new Random();
		
		int randpixel = rand.nextInt(height * width);
		pixel pixel;
		pixel = pixelarray.get(randpixel);
		int centroid1 = pixel.getRGB();
		
		
		return centroid1;
	
	}
	public static void createimage(){

		//this function just recreates the image from the values after kmeans is complete
		rbgarraycreate = new int[height][width];
		for(int i = 0;i< clusterarray.size();i++){
			ArrayList<pixel> array1 = clusterarray.get(i).getArray();
			for(int j = 0; j < array1.size(); j ++){
				int height1 = array1.get(j).getHeight();
				int width1 = array1.get(j).getWidth();
				rbgarraycreate[height1][width1] = array1.get(j).getRGB();
				
			}
			
		}
		
		
		
		b2 = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		for (int i=0;i<width;i++){
			for(int j=0; j<height;j++){
				Color c = new Color(rbgarraycreate[j][i],rbgarraycreate[j][i],rbgarraycreate[j][i]);
				int color1 = c.getRGB();
				 b2.setRGB(i, j, color1);
				 
			}
				}
		String path = "result.jpeg";
		File ImageFile = new File(path);
		
	
	    try
	    {
	        ImageIO.write(b2, "jpeg", ImageFile);
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
		
	}
	
}
