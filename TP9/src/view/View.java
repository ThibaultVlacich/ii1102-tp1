package view;

/**
 * Jeu de Go - View
 * 
 * @package view
 * 
 * @author Thibault Vlacich
 * @author Hugo Michard
 */

// Importe le package StdDraw
import edu.princeton.cs.introcs.StdDraw;

// Importe les modèles nécessaires
import models.Couleur;
import models.Goban;
import models.Joueur;

public class View {
  public static void drawGoban(Goban goban) {
    drawGoban(goban, false);
  }
  
  // Permet d'afficher le plateau de jeu
  public static void drawGoban(Goban goban, Boolean firstTime) {
    int taille = goban.getTaille();
    
    if (firstTime) {
      StdDraw.setCanvasSize(500, 500);
      
      StdDraw.setXscale(-1, taille + 1);
      StdDraw.setYscale(-1, taille + 1);
    }

    StdDraw.clear(StdDraw.YELLOW);
    
    StdDraw.setPenColor(StdDraw.BLACK);
    
    // Affiche les graduations de la grille
    for(int blabla = 0; blabla <= taille; blabla++) {
      // Abscisses
      StdDraw.text(blabla, -0.5, Integer.toString(blabla));
      // Ordonnées
      StdDraw.text(-0.5, blabla, Integer.toString(blabla));
    }
    
    // Dessine le quadrillage de la grille
    for(int i = 0; i < taille; i++) {
      for(int j = 0; j < taille; j++) {
        StdDraw.square(i + 0.5, j + 0.5, 0.5);
      }
    }
    
    // Place les pions sur la grille
    for(int i = 0; i <= taille; i++) {
      for(int j = 0; j <= taille; j++) {
        if(goban.getPlateau(i, j) != null) {
          Joueur joueur = goban.getPlateau(i, j);
          
          StdDraw.setPenColor(Couleur.getDrawColor(joueur.getCouleur()));
        
          StdDraw.filledCircle(j, i, 0.4);
        }
      }
    }
  }
  
  public static int drawGridSizeChoice() {
    StdDraw.setCanvasSize(500, 300);
    
    StdDraw.setXscale(0, 10);
    StdDraw.setYscale(0, 6);
    
    StdDraw.clear(StdDraw.WHITE);
    
    StdDraw.text(5, 5, "Jeu de Go");
    StdDraw.text(5, 4, "Veuillez choisir la taille de la grille");
    
    int[] availableSizes = {9, 13, 19};
    
    int offset = 0;
    
    // On dessine les choix de tailles
    for(int size: availableSizes) {
      StdDraw.square(2 + offset, 2, 1);
      
      StdDraw.text(2 + offset, 2, Integer.toString(size));
      
      offset += 3;
    }
    
    // On attend que l'utilisateur clique sur un des choix
    while (true) {
      if (StdDraw.mousePressed()) {
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        
        // Tempo pour éviter que StdDraw.mousePressed() reste à true
        try {
          Thread.sleep(100);
        } catch (Exception e) { }
        
        if (y < 1 || y > 3) {
          // L'utilisateur a cliqué ailleurs que sur un des choix
          continue;
        }
        
        if (x > 1 && x < 3) {
          return 9;
        } else if (x > 4 && x < 6) {
          return 13;
        } else if (x > 7 && x < 9) {
          return 19;
        } else {
          continue;
        }
      } else {
        continue;
      }
    }
  }
}
