package lt.vu.cdi;

import javax.enterprise.inject.Alternative;

@Alternative
public class TestNameGenerator implements NameGenerator {
    @Override
    public String generateUniqueName(String name, Long id) {
        return "T_" + name.substring(0, Math.min(2, name.length())) + "__" + id;
    }
}
