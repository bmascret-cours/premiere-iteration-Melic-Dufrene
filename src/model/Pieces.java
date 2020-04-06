package model;

public interface Pieces {
	public boolean capture();
	public Couleur getCouleur();
	public int getX();
	public int getY();
	public abstract boolean isMoveOk(int xFinal, int yFinal);
	public boolean move(int xFinal, int yFinal);
}
