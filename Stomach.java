package tama;



public class Stomach implements IStomach {
    //ATTRIBUTS
    private final int size;
    private final IEnergyStock energyStock;
    private int foodQuantity;
    private boolean isExploded;
    private boolean isWorking;
    
    //CONSTRUCTEUR
    public Stomach(int c, IEnergyStock stock)
    {
        if(c <= 0 || stock == null) {
            throw new AssertionError();
        }
        size = c;
        energyStock = stock;
        foodQuantity = 0;
        isExploded = false;
        isWorking = true;
    }
    // REQUETES
    public String describe() {
        return "stomach: " + foodQuantity + "/" + size + "/" 
                + (isWorking ? "fonctionnel" : "détruit");
    }
    
    public IEnergyStock energyStock() { 
        return energyStock;
    }
    
    public int foodQuantity() {
        return foodQuantity;
    }
    
    public boolean isEmpty() {
        return (foodQuantity == 0);
    }
    
    public boolean isExploded() {
        return foodQuantity > size;
    }
    
    public boolean isWorking() {
        return isWorking;
    }
    
    public int size() {
        return size;
    }
    
    //COMMANDES 
    public void evolve() {
        if (!isWorking()) {
            throw new AssertionError();
        }
        
        int x = foodQuantity();
        int y = energyStock.crtLevel();
        foodQuantity = x - (Math.min(x, DIGESTED_FOOD));
        isWorking = (x > 0) || (y >= NRJ_GAIN);
        
        if (x > 0) {
            energyStock.raiseLevel((Math.min(x, DIGESTED_FOOD) - 1) 
                                    * NRJ_GAIN);
        }
        
        if (x == 0) {
            energyStock.consume(Math.min(energyStock.crtLevel(), NRJ_GAIN));
        } 
        
    }
    
    public void fill(int qty) {
        if (!isWorking() || qty < 0) {
            throw new AssertionError();
        }
        
        foodQuantity = Math.min(size, foodQuantity) + qty;
        if (isExploded()) {
            isWorking = false;
        }
    }
}
