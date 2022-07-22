import java.util.TreeMap;

public class Armazem {

    private TreeMap<Ingrediente, Integer> estoque;

    public Armazem(TreeMap<Ingrediente, Integer> estoque) {
        this.estoque = estoque;
    }

    public TreeMap<Ingrediente, Integer> getEstoque() {
        return estoque;
    }

    public void setEstoque(TreeMap<Ingrediente, Integer> estoque) {
        this.estoque = estoque;
    }

}
