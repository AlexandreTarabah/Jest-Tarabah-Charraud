package modele.carte;

import modele.tas.Jest;
/**
 * Interface visitor qui possède la méthode abstraite visitJest qui visite le Jest. En effet l'application utilise le patron de conception visitor 
 *
 *
 */
public interface Visitor 
{
    void visitJest(Jest jest);
}