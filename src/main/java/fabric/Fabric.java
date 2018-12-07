package fabric;

import java.util.ArrayList;
import java.util.List;

public class Fabric {
    public static Integer conflictSquaresCount(List<String> dataSet) {

        List<ElfClaim> elfClaims = inputConverted(dataSet);

        for (ElfClaim elfClaim : elfClaims) {

        }

        throw new UnsupportedOperationException();
    }

    private static List<ElfClaim> inputConverted(List<String> dataSet) {

        List<ElfClaim> elfClaims = new ArrayList<>();

        for (String elfCaracteristic : dataSet) {
            Position position = new Position(elfCaracteristic.substring(elfCaracteristic.indexOf("@ ") + 2, elfCaracteristic.indexOf(":")));
            Size size = new Size(elfCaracteristic.substring(elfCaracteristic.indexOf(": ") + 2));
            elfClaims.add(new ElfClaim(position,size));
        }

        return elfClaims;
    }
}
