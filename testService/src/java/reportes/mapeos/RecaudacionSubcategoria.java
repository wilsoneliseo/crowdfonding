package reportes.mapeos;

public class RecaudacionSubcategoria {
    String nombreSub;
    int sum;

    public RecaudacionSubcategoria() {
    }

    public RecaudacionSubcategoria(String nombreSub, int sum) {
        this.nombreSub = nombreSub;
        this.sum = sum;
    }

    public String getNombreSub() {
        return nombreSub;
    }

    public void setNombreSub(String nombreSub) {
        this.nombreSub = nombreSub;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    
    
}
