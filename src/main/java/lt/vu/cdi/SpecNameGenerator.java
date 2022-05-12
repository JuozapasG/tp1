package lt.vu.cdi;

import javax.enterprise.inject.Specializes;

@Specializes
public class SpecNameGenerator extends ProdNameGenerator{
    public String extractId (String uniqueName){
        return uniqueName.split("_")[2];
    }
}
