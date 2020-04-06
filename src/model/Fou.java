package model;

public class Fou extends AbstractPiece implements Pieces{
	public Fou(Couleur couleur_Piece, Coord coordonnee_piece) {
		this.couleur_Piece = couleur_Piece;
		if (couleur_Piece == Couleur.BLANC) {
			this.nom_Piece="FOU_BLANC";
		}
		else if (couleur_Piece == Couleur.NOIR) {
			this.nom_Piece="FOU_NOIR";
		}
		this.coordonnee_Piece = coordonnee_piece;
	}

	public boolean isMoveOk(int xFinal,int yFinal) {
		if (xFinal == this.getX() && yFinal == this.getY()) {//Test si la pièce a bougé
			return false;
		}
		return Coord.coordonnees_valides(xFinal,yFinal) && (Math.abs(xFinal - this.getX()) == Math.abs(yFinal - this.getY()));
	}

	public static void main(String[] args) {
		Fou f = new Fou(Couleur.BLANC,new Coord(2,7));
		System.out.println(f.isMoveOk(3, 5));
	}
}
