package model;

public abstract class AbstractPiece{
	String nom_Piece;
	Coord coordonnee_Piece;
	Couleur couleur_Piece;
	
	public String toString() {
		return this.nom_Piece + this.coordonnee_Piece.toString() + "\n";		
	}
	public boolean capture() {
		this.coordonnee_Piece.x = -1;
		this.coordonnee_Piece.y = -1;
		return true;
	}
	
	public int getX() {
		return this.coordonnee_Piece.x;
	}
	
	public int getY() {
		return this.coordonnee_Piece.y;
	}
	
	public Couleur getCouleur() {
		return this.couleur_Piece;
	}
	
	abstract boolean isMoveOk(int xFinal, int yFinal);
	
	public boolean move(int xFinal, int yFinal) {
		if (isMoveOk(xFinal, yFinal)) {
			this.coordonnee_Piece.x = xFinal;
			this.coordonnee_Piece.y = yFinal;
			return true;
		}
		return false;
	}
}
