package model;

public class Roi extends AbstractPiece implements Pieces{
	boolean a_bouge = false;
	
	public Roi(Couleur couleur_Piece, Coord coordonnee_piece) {
		this.couleur_Piece = couleur_Piece;
		this.coordonnee_Piece = coordonnee_piece;
		if (couleur_Piece == Couleur.BLANC) {
			this.nom_Piece="ROI_BLANC";
		}
		else if (couleur_Piece == Couleur.NOIR) {
			this.nom_Piece="ROI_NOIR";
		}
	}

	public boolean isMoveOk(int xFinal,int yFinal) {
		if (xFinal == this.getX() && yFinal == this.getY()) {//Test si la pièce a bougé
			return false;
		}
		
		return Coord.coordonnees_valides(xFinal,yFinal) && Math.abs(this.getX() - xFinal)==1 && Math.abs(this.getY() - yFinal)==1;
	}
}