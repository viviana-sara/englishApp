package aplicatie;

import java.util.Objects;

public class Copil {
    private int id;
    private int idCopil;
    private int idParitne;
    private String rezultate;

    public Copil(int id, int idParitne, int idCopil, String rezultate){
        this.id = id;
        this.idParitne = idParitne;
        this.rezultate = rezultate;
        this.idCopil = idCopil;
    }

    public int getIdCopil() {
        return idCopil;
    }

    public void setIdCopil(int idCopil) {
        this.idCopil = idCopil;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getIdParitne() { return idParitne; }

    public void setIdParitne(int idParitne) { this.idParitne = idParitne; }

    public String getRezultate() { return rezultate; }

    public void setRezultate(String rezultate) { this.rezultate = rezultate; }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copil copil = (Copil) o;
        return id == copil.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
