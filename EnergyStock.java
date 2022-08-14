package tama;



public class EnergyStock implements IEnergyStock {
    //ATTRIBUTS
    private int crtLevel;

    //CONSTRUCTEUR
    public EnergyStock(int nrj) {
        if (nrj < 0) {
            throw new AssertionError("Nombre negatif!!");
        }
        
        crtLevel = nrj;
    }
    
    //REQUETES
    public int crtLevel() {
        return crtLevel;
    }
    
    public String describe() {
        return "nrjLevel: " + crtLevel();
    }
    
    public boolean isEmpty() {
        return (crtLevel() == 0);
    }
    
    //COMMANDES
    public void consume(int qty) {
        if (0 > qty || qty > crtLevel) {
            throw new AssertionError("Mauvaise valeur de l'energie!!");
        }
        
        crtLevel -= qty;
    }
    
    public void raiseLevel(int qty) {
        if (qty < 0) {
            throw new AssertionError("Mauvaise valeur de l'energie!!");
        }
        
        crtLevel += qty; 
    }
}
