public class Villain {

    private String villanName;
    private int villainHealth;
    private int villainMaxHealth;
    private boolean villainIsDead;
    private boolean villainIsAtMaxHealth;
    private boolean villainDefeated;

    public Villain(String name, int maxHealth) {
        villanName = name;
        villainMaxHealth = maxHealth;
        villainHealth = maxHealth;
        villainIsAtMaxHealth = true;
        villainDefeated = false;
    }

    public boolean checkIfDead() {
        if (villainHealth <= 0) {
            villainHealth = 0;
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean checkIfMaxHealth() {
        if (villainHealth >= villainMaxHealth) {
            villainHealth = villainMaxHealth;
            return true;
        }
        else {
            return false;
        }
    }

    public void doDamage(int damage) {
        villainHealth -= damage;
        villainIsDead = checkIfDead();
        if (villainIsDead) {
            //Error cannot do damage, villain is dead
        }
    }
    public void heal(int healAmount) {
        if (villainIsDead) {
            //Error cannot heal, villain is dead
        }
        villainHealth += healAmount;
        villainIsAtMaxHealth = checkIfMaxHealth();
    }
}
