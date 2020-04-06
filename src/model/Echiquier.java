package model;

import java.util.List;

public class Echiquier implements BoardGames {
	private Jeu jeu_blanc;
	private Jeu jeu_noir;
	public Couleur joueur_courant;
	private String message;
	
	public Echiquier() {
		jeu_blanc = new Jeu(Couleur.BLANC);
		jeu_noir = new Jeu(Couleur.NOIR);
		joueur_courant = Couleur.BLANC; //Les blancs commencent.
		message ="";
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	private void setMessage(String s) {
		this.message = s;
	}
	
	public void switchJoueur() {
		if (this.joueur_courant.equals(Couleur.BLANC)) {
			this.joueur_courant = Couleur.NOIR;
		}
		else {
			this.joueur_courant = Couleur.BLANC;
		}
	}
	
	@Override
	public Couleur getColorCurrentPlayer() {
		return joueur_courant;
	}
	
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
	    Couleur cInit = this.getPieceColor(xInit,yInit);
	    System.out.println("Couleur pièce: "+ cInit.toString());
	    if ( cInit == null || !(cInit.equals(joueur_courant))) {
	    	this.setMessage("Joueur invalide");
	        return false;
	    }
	    
	    if (!Coord.coordonnees_valides(xFinal, yFinal)) {
	    	this.setMessage("Coordonnées invalides");
	        return false;
	    }
	    		
	    if (xInit == xFinal && yInit == yFinal) {
	    	this.setMessage("Coordonnées de départ et d'arrivée identiques");
	        return false;
	    }

	    if (!(jeu_blanc.isMoveOk( xInit, yInit, xFinal, yFinal)) && !(jeu_noir.isMoveOk( xInit, yInit, xFinal, yFinal))) {
	    	this.setMessage("Deplacement invalide");
	    	return false;
	    }

	    //TODO gestion pièces sur le chemin

	    Couleur cFinale = this.getPieceColor(xFinal,yFinal);
	    if (cFinale == null) {
	    	this.setMessage("OK : déplacement sans capture");
	    	return true;
	    }
	    
	    if (cFinale.equals(cInit)) {
	    	this.setMessage("Il y a une pièce de même couleur à l'arrivée");
	        if (joueur_courant.equals(Couleur.BLANC)) {
	            jeu_blanc.setCastling();
	        }
	        else {
	            jeu_noir.setCastling();
	        }
	    }
	    else if (joueur_courant.equals(Couleur.BLANC)) {
	    	this.setMessage("Possible_capture");
	        jeu_noir.setPossibleCapture();
	    }
	    else if (joueur_courant.equals(Couleur.NOIR)) {
	    	this.setMessage("Possible_capture");
	        jeu_blanc.setPossibleCapture();
	    }
	    return false;
	}

	
	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		if (this.isMoveOk(xInit, yInit, xFinal, yFinal)) {
			if (joueur_courant.equals(Couleur.BLANC)) {
				return jeu_blanc.move(xInit, yInit, xFinal, yFinal);
			}
			else {
				return jeu_noir.move(xInit, yInit, xFinal, yFinal);
			}
		}
		return false;
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Couleur getPieceColor(int x, int y) {
		if (jeu_blanc.getPieceColor(x, y)== null) {
			return jeu_noir.getPieceColor(x, y);
		}
		else {
			return Couleur.BLANC;
		}
	}

	public List<PieceIHM> getPiecesIHM() {
		List<PieceIHM> liste_blanche = jeu_blanc.getPiecesIHM();
		List<PieceIHM> liste_noire = jeu_noir.getPiecesIHM();
		liste_blanche.addAll(liste_noire);
		return liste_blanche;
	}
	
	public String toString() {
		String s = "";
		s = s + "Jeu blanc:\n" + jeu_blanc.toString() + "\n\n";
		s = s + "Jeu noir:\n" + jeu_noir.toString() + "\n";
		s = s + "Au joueur "+joueur_courant.toString()+" de jouer.";
		return s;
	}
	
	public static void main(String[] args) {
		Echiquier e = new Echiquier();
		System.out.println(e.toString());
	}
}