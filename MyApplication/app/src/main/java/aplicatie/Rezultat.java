package aplicatie;

public class Rezultat {
    private int id;
    private int  idCopil;
    private String idTest;
    private String raspuns;
    private String nota;
    private String dificultate;

    public Rezultat(int id, int idCopil, String idTest, String raspuns, String nota, String dificultate){
        this.dificultate = dificultate;
        this.id = id;
        this.idCopil = idCopil;
        this.idTest = idTest;
        this.nota = nota;
        this.raspuns =raspuns;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getIdCopil() { return idCopil; }

    public void setIdCopil(int idCopil) { this.idCopil = idCopil; }

    public String getIdTest() { return idTest; }

    public void setIdTest(String idTest) { this.idTest = idTest; }

    public String getRaspuns() { return raspuns; }

    public void setRaspuns(String raspuns) { this.raspuns = raspuns; }

    public String getNota() { return nota; }

    public void setNota(String nota) { this.nota = nota; }

    public String getDificultate() { return dificultate; }

    public void setDificultate(String dificultate) { this.dificultate = dificultate; }
}
