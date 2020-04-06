package model;

public class Cavalier extends AbstractPiece implements Pieces{
	public Cavalier(Couleur couleur_Piece, Coord coordonnee_piece) {
		this.couleur_Piece = couleur_Piece;
		if (couleur_Piece == Couleur.BLANC) {
			this.nom_Piece="CAVALIER_BLANC";
		}
		else if (couleur_Piece == Couleur.NOIR) {
			this.nom_Piece="CAVALIER_NOIR";
		}
		this.coordonnee_Piece = coordonnee_piece;
	}

	public boolean isMoveOk(int xFinal,int yFinal) {
		boolean depl_OK = false;
		if (xFinal == this.getX() && yFinal == this.getY()) {//Test si la pièce a bougé
			return false;
		}
		if ( Math.abs(this.getX() - xFinal) == 1 && Math.abs(this.getY() - yFinal) == 2 ) {
			depl_OK = true;
		}
		else if ( Math.abs(this.getX() - xFinal) == 2 && Math.abs(this.getY() - yFinal) == 1 ) {
			depl_OK = true;
		}
		return Coord.coordonnees_valides(xFinal,yFinal) && depl_OK;
	}
}
