package model;

import java.util.List;
import java.util.LinkedList;
public class Jeu {
	Couleur couleur;
	List<Pieces> pieces;
	
	public Jeu(Couleur color) {
		this.couleur=color;
		this.pieces=tools.ChessPiecesFactory.newPieces(color);
	}
	
	public boolean isPieceHere(int x, int y) {
		for (Pieces p : this.pieces) {
			if (p.getX()==x && p.getY()==y) {
				return true;
			}
		}
		return false;
	}
	public boolean isMoveOk(int xInit,int yInit,int xFinal,int yFinal) {
		Pieces p = findPiece(xInit,yInit);
		if (p == null) { return false;}
		return p.isMoveOk(xFinal,yFinal);
	}
	
	public boolean move(int xInit,int yInit,int xFinal,int yFinal) {
		Pieces p = findPiece(xInit,yInit);
		if (p == null) { return false;}
		return p.move(xFinal,yFinal);
	}
	
	private Pieces findPiece(int x,int y) {
		Pieces piece_trouvee=null;
		for (Pieces p : this.pieces) {
			if (p.getX()==x && p.getY()==y) {
				piece_trouvee = p;
			}
		}
		return piece_trouvee;
	}
	
	public boolean capture(int x, int y) {
		Pieces piece_capturee=findPiece(x,y);
		if (piece_capturee != null) {
			piece_capturee.capture();
			return true;
		}
		return false;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public Coord getKingCoord() {
		for (Pieces p : this.pieces) {
			if (p.equals("Roi")) {
				return new Coord(p.getX(),p.getY());
			}
		}
		return null;
	}
	
	
	public String toString() {
		return "";
	}
}