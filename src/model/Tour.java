package model;


public class Tour extends AbstractPiece implements Pieces{
	boolean a_bouge = false;
	
	public Tour(Couleur couleur_Piece, Coord coordonnee_piece) {
		this.couleur_Piece = couleur_Piece;
		if (couleur_Piece == Couleur.BLANC) {
			this.nom_Piece="TOUR_BLANCHE";
		}
		else if (couleur_Piece == Couleur.NOIR) {
			this.nom_Piece="TOUR_NOIRE";
		}
		this.coordonnee_Piece = coordonnee_piece;
	}

	public boolean isMoveOk(int xFinal,int yFinal) {
		if (xFinal == this.getX() && yFinal == this.getY()) {//Test si la pièce a bougé
			return false;
		}
		return Coord.coordonnees_valides(xFinal,yFinal) && (xFinal==this.getX() || yFinal==this.getY());
	}
	
	public boolean move(int xFinal, int yFinal) {
		if (isMoveOk(xFinal, yFinal)) {
			this.coordonnee_Piece.x = xFinal;
			this.coordonnee_Piece.y = yFinal;
			return true;
		}
		this.a_bouge = true;
		return false;
	}

	public static void main(String[] args) {
		Tour t = new Tour(Couleur.BLANC,new Coord(7,0));
		System.out.println(t.isMoveOk(7, 3));
	}
}
