package modele.carte;

import modele.tas.Jest;
/**
 * Interface visitor qui poss�de la m�thode abstraite visitJest qui visite le Jest. En effet l'application utilise le patron de conception visitor 
 *
 *
 */
public interface Visitor 
{
    void visitJest(Jest jest);
}