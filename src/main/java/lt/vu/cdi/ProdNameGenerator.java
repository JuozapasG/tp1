package lt.vu.cdi;

public class ProdNameGenerator implements NameGenerator {
    @Override
    public String generateUniqueName(String name, Long id) {
        return "P_" + name.substring(0, Math.min(2, name.length())) + "_" + id;
    }
}
