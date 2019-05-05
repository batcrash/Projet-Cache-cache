import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class Fenetre extends JFrame implements ActionListener, MouseListener  {
		
		  JPanel panel = new JPanel();
		  JPanel deck = new Deck();
		  JLabel label = new JLabel();
		  JButton button = new JButton();
		  JMenuBar menu = new JMenuBar();
		  JMenu decksMenu = new JMenu("Decks");
		  JMenuItem itemAnimaux = new JMenuItem("Animaux"),
					itemVoitures = new JMenuItem("Voitures"),
					itemPaysages = new JMenuItem("Paysages");
		  JMenu levelMenu = new JMenu("Niveau de difficultés");
		  JRadioButtonMenuItem itemFacile = new JRadioButtonMenuItem("Facile"),
				  			   itemNormal = new JRadioButtonMenuItem("Normal"),
				  			   itemDifficile = new JRadioButtonMenuItem("Difficile");
		  ButtonGroup levelGroupe = new ButtonGroup();
			
		  
		  int nbClick=0;
		  
		  //ArrayList<Deck> allA = new ArrayList<Deck>();
		    
		  public Fenetre() {   
			
		    this.setTitle("Cache-Cache");
		    this.setSize(610, 625);
		    this.setLocationRelativeTo(null); 
		    this.setResizable(false);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		    decksMenu.add(itemAnimaux);
		    decksMenu.add(itemVoitures);
		    decksMenu.add(itemPaysages);
		    menu.add(decksMenu);
		    
			levelGroupe.add(itemFacile);
			levelGroupe.add(itemNormal);
			levelGroupe.add(itemDifficile);
			itemNormal.setSelected(true);
		    
		    levelMenu.add(itemFacile);
		    levelMenu.add(itemNormal);
		    levelMenu.add(itemDifficile);
		    menu.add(levelMenu);
		    
		    this.setJMenuBar(menu);
		    
		    panel.setLayout(new BorderLayout());
		    panel.add(deck,BorderLayout.CENTER);
			    label.setText("   But du jeux :  retrouver toutes les paires.");
			    label.setVisible(true);
		    panel.add(label,BorderLayout.NORTH);
		    	button.addActionListener(this);
			    button.setText("Recommencer");
			    button.setVisible(true);
		    panel.add(button,BorderLayout.SOUTH);
		  
		    this.setContentPane(panel);
		    this.setVisible(true);
		    
		    this.addMouseListener (this);
		    
		    itemAnimaux.addActionListener(modifierDeckListener);
		    itemVoitures.addActionListener(modifierDeckListener);
		    itemPaysages.addActionListener(modifierDeckListener);
		    
		    itemFacile.addActionListener(modifierLevelListener);
		    itemNormal.addActionListener(modifierLevelListener);
		    itemDifficile.addActionListener(modifierLevelListener);

		    
		  }
			
		  	ActionListener modifierDeckListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (e.getActionCommand().equals("Animaux")) { ((Deck) deck).setPath("Animaux");  }
					else if (e.getActionCommand().equals("Voitures")) { ((Deck) deck).setPath("Voitures"); }
					else if (e.getActionCommand().equals("Paysages")) { ((Deck) deck).setPath("Paysages"); }
					
					((Deck) deck).initGame();
					repaint();
					setVisible(true);
				}
			};

			ActionListener modifierLevelListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (e.getActionCommand().equals("Facile")) { ((Deck) deck).setNbImages(6); ((Deck) deck).setTailleImage(150); ((Deck) deck).setRecadrage(-2); }
					else if (e.getActionCommand().equals("Normal")) { ((Deck) deck).setNbImages(15); ((Deck) deck).setTailleImage(100); ((Deck) deck).setRecadrage(0); }
					else if (e.getActionCommand().equals("Difficile")) { ((Deck) deck).setNbImages(28); ((Deck) deck).setTailleImage(75); ((Deck) deck).setRecadrage(2);}
					
					((Deck) deck).initGame();
					repaint();
					setVisible(true);
				}
			};
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (nbClick == 0) {
					((Deck) deck).setXClick1(e.getX()); 
					((Deck) deck).setYClick1(e.getY()-70); 
					repaint();
					nbClick++;
				}
				else {
					((Deck) deck).setXClick2(e.getX()); 
					((Deck) deck).setYClick2(e.getY()-70); 
					repaint();
					
					nbClick=0;
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}



			@Override
			public void actionPerformed(ActionEvent e) {
				((Deck) deck).reinitGame();
				repaint();
			}
			

}

