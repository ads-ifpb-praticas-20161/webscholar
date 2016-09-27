package dac.webscholar.shared.entities;

/**
 * Created by marcusviniv on 26/09/2016.
 */
public enum Intervalo {

    M1("M1", "7:00", "7:50"),
    M2("M2", "7:50", "8:40"),
    M3("M3", "8:40", "9:30"),
    M4("M4", "9:45", "10:35"),
    M5("M5", "10:35", "11:25"),
    M6("M6", "11:25", "12:15"),

    T1("T1", "13:00", "13:50"),
    T2("T2", "13:50", "14:40"),
    T3("T3", "14:40", "15:30"),
    T4("T4", "15:45", "16:35"),
    T5("T5", "16:35", "17:25"),
    T6("T6", "17:25", "18:15"),

    N1("N1", "18:50", "19:40"),
    N2("N2", "19:40", "20:30"),
    N3("N3", "20:40", "21:30"),
    N4("N4", "21:30", "22:20"),
    N5("N5", "22:20", "23:10");

    Intervalo(String code, String inicio, String fim){
        this.code = code;
        this.inicio = inicio;
        this.fim = fim;
    }

    private String code;
    private String inicio;
    private String fim;

    public String getCode(){
        return code;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFim() {
        return fim;
    }
}
