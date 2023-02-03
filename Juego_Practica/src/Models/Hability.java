package Models;
public class Hability {
    String id;
    String name;
    String descripcion;
    int manaUse;
    double baseDamage;
    double escalado;
    String estatEscalado;
    String type;
    String effect;
    double probEffect;

    public Hability(String id, String name,String descripcion,int manaUse, double baseDamage, double escalado,String estatEscalado, String type, String effect, double probEffect) {
        this.id = id;
        this.name = name;
        this.baseDamage = baseDamage;
        this.escalado = escalado;
        this.type = type;
        this.effect = effect;
        this.probEffect = probEffect;
        this.estatEscalado=estatEscalado;
        this.descripcion=descripcion;
        this.manaUse=manaUse;
    }

    public Hability(String id) {
        this.id = id;
    }

    public int getManaUse() {
        return manaUse;
    }

    public void setManaUse(int manaUse) {
        this.manaUse = manaUse;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public double getEscalado() {
        return escalado;
    }

    public void setEscalado(double escalado) {
        this.escalado = escalado;
    }

    public String getEstatEscalado() {
        return estatEscalado;
    }

    public void setEstatEscalado(String estatEscalado) {
        this.estatEscalado = estatEscalado;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public double getProbEffect() {
        return probEffect;
    }

    public void setProbEffect(double probEffect) {
        this.probEffect = probEffect;
    }
}
