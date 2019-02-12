package entities;

public class Monster {
    private String name;
    private float cr;
    private int exp;
    private String size;
    private String type;
    private String[] tags;
    private String alignment;
    private int hp;
    private int init;
    private String[] environment;

    public Monster(String name, float cr, int exp, String size, String type, String[] tags, String alignment, int hp, int init, String[] environment) {
        this.name = name;
        this.cr = cr;
        this.exp = exp;
        this.size = size;
        this.type = type;
        this.tags = tags;
        this.alignment = alignment;
        this.hp = hp;
        this.init = init;
        this.environment = environment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCr() {
        return cr;
    }

    public String getCrString() {
        if (cr == 0)
            return "0";

        return (cr < 1 ? "1/" + ((int) (1 / cr)) : (int) cr).toString();
    }

    public void setCr(float cr) {
        this.cr = cr;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }

    public String[] getEnvironment() {
        return environment;
    }

    public void setEnvironment(String[] environment) {
        this.environment = environment;
    }

    public String toString() {
        return String.format("%s (CR %s, %d exp), %s %s (%d HP), %s init.", name, getCrString(), exp, size, type, hp, (init > 0 ? String.format("%s%d", "+", init) : init));
    }

    public Monster clone() {
        return new Monster(name, cr, exp, size, type, tags, alignment, hp, init, environment);
    }
}
