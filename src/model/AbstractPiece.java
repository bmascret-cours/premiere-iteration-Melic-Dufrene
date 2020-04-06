package model;

public abstract class AbstractPiece{
	String nom_Piece;
	Coord coordonnee_Piece;
	Couleur couleur_Piece;
	
	public String toString() {
		return this.nom_Piece + this.coordonnee_Piece.toString();		
	}
	public final boolean capture() {
		this.coordonnee_Piece.x = -1;
		this.coordonnee_Piece.y = -1;
		return true;
	}
	
	public final int getX() {
		return this.coordonnee_Piece.x;
	}
	
	public final int getY() {
		return this.coordonnee_Piece.y;
	}
	
	public final Couleur getCouleur() {
		return this.couleur_Piece;
	}
	
	abstract boolean isMoveOk(int xFinal, int yFinal);
	
	public boolean move(int xFinal, int yFinal) {
		this.coordonnee_Piece.x = xFinal;
		this.coordonnee_Piece.y = yFinal;
		return true;
	}
}
