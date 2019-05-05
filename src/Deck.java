
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Deck extends JPanel {
	
	
	public Deck() {
		
		nbImages = 15;
		tailleImage = 100;
		recadrage = 0;
		path = "Animaux";
		initGame();
	}

	Image blanc;
	ArrayList<Image> image;
	ArrayList<Integer> imagePrise;
	HashMap<Integer, Boolean> zoneDecouverte;
 
	private double xClick1, yClick1, xClick2, yClick2;
	private int nbImages, tailleImage,recadrage, zoneClick1, zoneClick2, testClick1, testClick2;
	private String path;
	
	public void setNbImages(int nbi) {
		nbImages=nbi;
	}
	
	public void setTailleImage(int ti) {
		tailleImage=ti;
	}
	
	public void setRecadrage(int rec) {
		recadrage=rec;
	}
	
	public void setPath(String path) {
		this.path=path;
	}
	
	public void setXClick1(double d) {
		this.xClick1 = d;
	}
	
	public void setYClick1(double d) {
		this.yClick1 = d;
	}
	
	public void setXClick2(double d) {
		this.xClick2 = d;
	}
	
	public void setYClick2(double d) {
		this.yClick2 = d;
	}
	
	
	
	public void modifPaint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g.create();
	
		int k=0;
		boolean win = true;
		for (int i=0; i<(6+recadrage)*tailleImage; i=i+tailleImage) {
			for (int j=0; j<(5+recadrage)*tailleImage; j=j+tailleImage) {
				
				
				if (xClick1>i && xClick1<i+tailleImage && yClick1>j && yClick1<j+tailleImage) { 
					zoneClick1 = k; testClick1 = imagePrise.get(k);
				}
				else if (xClick2>i && xClick2<i+tailleImage && yClick2>j && yClick2<j+tailleImage) { 
					zoneClick2 = k; testClick2 = imagePrise.get(k);
				}
				else { 
					if (testClick1 == testClick2-nbImages || testClick1 == testClick2+nbImages) { zoneDecouverte.put(zoneClick1, true); zoneDecouverte.put(zoneClick2, true); }
					if (zoneDecouverte.get(k) != true) {
						g2d.setPaint(Color.white);
						g2d.fillRect(i, j, tailleImage, tailleImage);
						win = false;
					}
				}
				
				
			k++;
			g2d.setColor(Color.black);
			g2d.drawRect(i, j, tailleImage, tailleImage);
			}
		}
		
		if (win == true) {
			try {
				g.drawImage(ImageIO.read(new File("Images/winner.png")), 0, 0, 600, 525, this);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} 
		}
	}
	
	public void paintComponent(Graphics g) {

		int k=0;
		for (int i=0; i<(6+recadrage)*tailleImage; i=i+tailleImage) {
			for (int j=0; j<(5+recadrage)*tailleImage; j=j+tailleImage) {
					
					g.drawImage(image.get(imagePrise.get(k)), i, j, tailleImage, tailleImage, this);
					k++;
			}
		}
        modifPaint(g);
    }
	
	public void initGame() {
		
		image = new ArrayList<Image>();
	    try {
	    	for (int i=0; i<nbImages; i++) {image.add(ImageIO.read(new File("Images/" +path+ "/" +i+ ".png"))); }
	    	for (int i=0; i<nbImages; i++) {image.add(ImageIO.read(new File("Images/" +path+ "/" +i+ ".png"))); }
	    }
	    catch (IOException e) {
		      e.printStackTrace();
		}
	    
	    this.reinitGame();
	}
	    

	
	public void reinitGame () {
		
		zoneClick1=-1; zoneClick2=-1;
		xClick1=-1; yClick1=-1;
		xClick2=-1; yClick2=-1;
		
		imagePrise = new ArrayList<Integer>();
	    int aleaImage;
	    
		for (int j=0; j<nbImages*2; j++) {
			do {
				aleaImage = (int)(Math.random()*(nbImages*2));
			} while (imagePrise.contains(aleaImage));
			imagePrise.add(aleaImage);	
		}
		
		zoneDecouverte = new HashMap<Integer, Boolean>();
		for (int i=0; i<nbImages*2; i++) { zoneDecouverte.put(i,false); }
		
	}
	
}