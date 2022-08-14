package tama;




public class Heart implements IHeart {
    // ATTRIBUTS
    private final int maxLifeTime;
    private int lifeTime;
    private int age;
    private final IEnergyStock energyStock;
    private boolean isBeating;
    
    //CONSTRUCTEUR
    public Heart(int max, IEnergyStock nrj) {
        if (max <= 0 || nrj == null) {
            throw new AssertionError("Mauvaises valeurs");
        }
        
        age = 0;
        maxLifeTime = max;
        lifeTime = max;
        energyStock = nrj;
        isBeating = true;
    }
    
    //REQUETES
    public int age() {
        return age;
    }

    public String describe() {
       return  "heart: " + age + "/" + lifeTime + "/" + maxLifeTime 
                + "/" + (isBeating ? "vivant" : "mort");
    }
    
    public int lifeTime() {
        return lifeTime;
    }
 
    public IEnergyStock energyStock() {
        return energyStock;
    }
    
    public int maxLifeTime() {
        return maxLifeTime;
    }
    
    public boolean isBeating() {
        return isBeating;
    }
    
    //COMMANDES
    public void evolve() {
        if (!isBeating()) {
            throw new AssertionError("Tamagotchi,mort!!");
        }
        
        int x = lifeTime();
        int energyAvailable = energyStock.crtLevel() - ENERGY_NEEDS;
        int lifeTimeDeficit = maxLifeTime() - x;
        int min = Math.min(energyAvailable, lifeTimeDeficit);
        
        age += 1;
        if (energyStock().crtLevel() <= ENERGY_NEEDS) {
            lifeTime = Math.max(age(), x + energyAvailable);
            energyStock.consume(energyStock.crtLevel());
        } 
        
        if (energyStock().crtLevel() > ENERGY_NEEDS) {
            lifeTime += min;
            energyStock.consume(energyAvailable - min); 
        }
        isBeating = age() < lifeTime();
    }
    
    public void stopBeating() {
        isBeating = false;
    }
}
