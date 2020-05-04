package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import tools.ChessImageProvider;

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {

	ChessGameControler chessGameControler;
	Dimension dim;
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	Coord Init;

	public   ChessGameGUI(String titre,ChessGameControler chessGameControler2,Dimension dim) {
		this.chessGameControler = chessGameControler2;
		this.dim = dim;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(chessGameControler.getMessage() + "\n");	

		  //  Use a Layered Pane for this this application
				layeredPane = new JLayeredPane();
				getContentPane().add(layeredPane);
				layeredPane.setPreferredSize(this.dim);
				layeredPane.addMouseListener(this);
				layeredPane.addMouseMotionListener(this);

				  //Add a chess board to the Layered Pane 
				 
				chessBoard = new JPanel();
				layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
				chessBoard.setLayout( new GridLayout(8, 8) );
				chessBoard.setPreferredSize( this.dim );
				chessBoard.setBounds(0, 0, this.dim.width, this.dim.height);
				 
				for (int i = 0; i < 64; i++) {
				JPanel square = new JPanel( new BorderLayout() );
				chessBoard.add( square );
				 
				int row = (i / 8) % 2;
					if (row == 0)
						square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
					else
						square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
				}
		
		List<PieceIHM> piecesIHM = (List<PieceIHM>) arg;
		 
		
		for (PieceIHM pieceIHM : piecesIHM) {	
			for(Coord coord : pieceIHM.getList()) {
				JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile(pieceIHM.getTypePiece(),pieceIHM.getCouleur())) );
				JPanel panel = (JPanel)chessBoard.getComponent(8*coord.y+coord.x);
				panel.add(piece);
			}
		}
	}

	public void mousePressed(MouseEvent e){
		  chessPiece = null;
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		 
		  if (c instanceof JPanel) 
		  return;
		  
		  this.Init = new Coord(e.getX(),e.getY());
		  
		  Point parentLocation = c.getParent().getLocation();
		  xAdjustment = parentLocation.x - e.getX();
		  yAdjustment = parentLocation.y - e.getY();
		  chessPiece = (JLabel)c;
		  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
		  }
		 
		  //Move the chess piece around
		  
		  public void mouseDragged(MouseEvent me) {
		  if (chessPiece == null) return;
		 chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
		 }
		 
		  //Drop the chess piece back onto the chess board
		 
		  public void mouseReleased(MouseEvent e) {
		  if(chessPiece == null) return;
		  
		  Coord initCoord = new Coord((int) (this.Init.x*8/dim.getWidth()),(int) (this.Init.y*8/dim.getHeight()));
		  Coord arriveeCoord = new Coord((int) (e.getX()*8/dim.getWidth()),(int) (e.getY()*8/dim.getHeight()));
		  
		  if (this.chessGameControler.move(initCoord,arriveeCoord) == false) {
			  System.out.println("initCoord: " + initCoord + " arriveeCoord: " + arriveeCoord);
			  return;
		  }
		  chessPiece.setVisible(false);
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		 
		  if (c instanceof JLabel){
		  Container parent = c.getParent();
		  parent.remove(0);
		  parent.add( chessPiece );
		  }
		  else {
		  Container parent = (Container)c;
		  parent.add( chessPiece );
		  }
		 
		  chessPiece.setVisible(true);
		  }
		 
		  public void mouseClicked(MouseEvent e) {
		  
		  }
		  public void mouseMoved(MouseEvent e) {
			  
		  }
		  public void mouseEntered(MouseEvent e){
		  
		  }
		  public void mouseExited(MouseEvent e) {
		  
		  }
		  
		  private Coord transform(int xFenetre,int yFenetre) {
			  Coord coord = new Coord(0,0);
			  return coord;
			  
		  }
}
