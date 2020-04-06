package model;

public class Reine extends AbstractPiece implements Pieces{
	public Reine(Couleur couleur_Piece, Coord coordonnee_piece) {
		this.couleur_Piece = couleur_Piece;
		if (couleur_Piece == Couleur.BLANC) {
			this.nom_Piece="REINE_BLANCHE";
		}
		else if (couleur_Piece == Couleur.NOIR) {
			this.nom_Piece="REINE_NOIRE";
		}
		this.coordonnee_Piece = coordonnee_piece;
	}

	public boolean isMoveOk(int xFinal,int yFinal) {
		boolean depl_OK = false;
		if (xFinal == this.getX() && yFinal == this.getY()) {//Test si la pi�ce a boug�
			return false;
		}
		if (Math.abs(xFinal - this.getX()) == Math.abs(yFinal - this.getY())) {//test si la Reine se d�place en diagonal
			depl_OK = true;
		}
		else if (this.getX() == xFinal || this.getY() == yFinal) {//test si la reine se d�place en ligne droite
			depl_OK = true;
		}
		return Coord.coordonnees_valides(xFinal,yFinal) && depl_OK;
	}
}