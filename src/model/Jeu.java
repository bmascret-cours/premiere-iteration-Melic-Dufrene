package model;

import java.util.LinkedList;
import java.util.List;

public class Jeu {
	private Couleur couleur;
	private List<Pieces> pieces;
	private boolean castling_possible;
	
	public Jeu(Couleur color) {
		this.couleur=color;
		this.pieces=tools.ChessPiecesFactory.newPieces(color);
		castling_possible = false;
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
			if (p.getClass().equals(Roi.class)) {
				return new Coord(p.getX(),p.getY());
			}
		}
		return null;
	}
	
	public Couleur getPieceColor(int x, int y) {
		if (this.isPieceHere(x, y)) {
			return this.couleur;
		}
		else {
			return null;
		}
	}
	
	public List<PieceIHM> getPiecesIHM(){
		PieceIHM newPieceIHM = null;
		List<PieceIHM> list = new LinkedList<PieceIHM>();
		for (Pieces piece : pieces){
		boolean existe = false;
		// si le type de piece existe déjà dans la liste de PieceIHM
		// ajout des coordonnées de la pièce dans la liste de Coord de ce type
		// si elle est toujours en jeu (x et y != -1)
		for ( PieceIHM pieceIHM : list){
		if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){
		existe = true;
		if (piece.getX() != -1){
		pieceIHM.add(new Coord(piece.getX(), piece.getY()));
		}
		}
		}
		// sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
		if (! existe) {
		if (piece.getX() != -1){
		newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(),
		piece.getCouleur());
		newPieceIHM.add(new Coord(piece.getX(), piece.getY()));
		list.add(newPieceIHM);
		}
		}
		}
		return list;
	}
	
	public String getPieceType(int x, int y) {
		Pieces p = findPiece(x,y);
		return p.getClass().toString();
	}
	
	public boolean isMoveOk(int xInit,int yInit,int xFinal,int yFinal) {
		Pieces p = findPiece(xInit,yInit);
		if (p == null) { return false;}
		return p.isMoveOk(xFinal,yFinal);
	}
	
	public boolean isPawnPromotion(int xFinal, int yFinal) {
		if (this.couleur.equals(Couleur.BLANC)) {
			return (yFinal == 0);
		}
		else if (this.couleur.equals(Couleur.NOIR)) {
			return (yFinal == 7);
		}
		else {
			return false;
		}
	}

	public boolean isPieceHere(int x, int y) {
		for (Pieces p : this.pieces) {
			if (p.getX()==x && p.getY()==y) {
				return true;
			}
		}
		return false;
	}
	
	public boolean move(int xInit,int yInit,int xFinal,int yFinal) {
		Pieces p = findPiece(xInit,yInit);
		if (p == null) {
			System.out.println("Erreur emplacement pièce.");
			return false;
		}
		return p.move(xFinal,yFinal);
	}
	
	public boolean pawnPromotion(int x, int y, String type) {
		if (isPawnPromotion(x,y)) {
			Pieces p = findPiece(x,y);
			this.pieces.remove(p);
			this.pieces.add(tools.ChessSinglePieceFactory.newPiece(this.couleur,type, x,y));
			return true;
		}
		return false;
	}
	
	public void setCastling() {
		//TODO
		this.castling_possible = false;
	}
	
	public void setPossibleCapture() {
		//TODO
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Pieces p : this.pieces) {
			if (p.getX() != -1 && p.getY() != -1) {
				s = s + p.toString() + "\n";
			}
		}
		return s;
	}
	
	public void undoCapture() {
		//TODO
	}
	
	public void undoMove() {
		//TODO
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
	
	public static void main(String[] args) {
		Jeu j = new Jeu(Couleur.NOIR);
		System.out.println(j.isMoveOk(7, 0, 7, 4));
	}
}