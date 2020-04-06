package model;

public class Pion extends AbstractPiece implements Pieces{
	boolean a_bouge = false;
	
	public Pion(Couleur couleur_Piece, Coord coordonnee_piece) {
		this.couleur_Piece = couleur_Piece;
		if (couleur_Piece == Couleur.BLANC) {
			this.nom_Piece="PION_BLANC";
		}
		else if (couleur_Piece == Couleur.NOIR) {
			this.nom_Piece="PION_NOIR";
		}
		this.coordonnee_Piece = coordonnee_piece;
	}

	public boolean isMoveOk(int xFinal,int yFinal) {
		boolean depl_OK = false;
		if (xFinal != this.getX()) {
			return false;
		}
		if (this.getCouleur().equals(Couleur.BLANC)) {
				if (yFinal==this.getY()-1) {
					depl_OK = true;
				}
				else if (!a_bouge && yFinal==this.getY()-2) {
					depl_OK = true;
				}
		}
		else if (this.getCouleur().equals(Couleur.NOIR)) {
			if (yFinal==this.getY()+1) {
				depl_OK = true;
			}
			else if (!a_bouge && yFinal==this.getY()+2) {
				depl_OK = true;
			}
		}
		return Coord.coordonnees_valides(xFinal,yFinal) && depl_OK;
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
		Pion p = new Pion(Couleur.BLANC,new Coord(3,4));
		System.out.println(p.isMoveOk(4,3));
	}
}