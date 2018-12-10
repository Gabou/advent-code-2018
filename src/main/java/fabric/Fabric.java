package fabric;

import frequency.Frequency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Fabric {

    private final HashMap<Position, List<ID>> elfClaimIDByPosition;
    private final List<ElfClaim> elfClaims;

    public Fabric(List<String> dataSet) {
        elfClaimIDByPosition = new HashMap<>();

        elfClaims = inputConverted(dataSet);

        for (ElfClaim elfClaim : elfClaims) {
            applyElfClaim(elfClaim);
        }

    }

    private void applyElfClaim(ElfClaim elfClaim) {

        for (Position position : elfClaim.zone()) {
            List<ID> ids = new ArrayList<>();
            ids.add(elfClaim.id());
            List<ID> iDs = elfClaimIDByPosition.putIfAbsent(position, ids);

            if (iDs != null) {
                iDs.add(elfClaim.id());
            }
        }
    }

    public int conflictSquaresCount() {

        int conflictSquaresCounter = 0;
        for (Map.Entry<Position, List<ID>> positionListEntry : elfClaimIDByPosition.entrySet()) {
            if (positionListEntry.getValue().size() > 1) {
                conflictSquaresCounter++;
            }
        }

        return conflictSquaresCounter;
    }

    private List<ElfClaim> inputConverted(List<String> dataSet) {

        List<ElfClaim> elfClaims = new ArrayList<>();

        for (String elfCaracteristic : dataSet) {
            ID id = new ID(elfCaracteristic.substring(elfCaracteristic.indexOf("#") +1, elfCaracteristic.indexOf(" ")));
            Position position = new Position(elfCaracteristic.substring(elfCaracteristic.indexOf("@ ") + 2, elfCaracteristic.indexOf(":")));
            Size size = new Size(elfCaracteristic.substring(elfCaracteristic.indexOf(": ") + 2));
            elfClaims.add(new ElfClaim(id, position,size));
        }

        return elfClaims;
    }

    public static void main(String[] args) throws IOException {
        List<String> input = input("/inputDay3");

        Fabric fabric = new Fabric(input);

        System.out.println(fabric.conflictSquaresCount());
        System.out.println(fabric.claimWithoutConflictSquares());
    }

    private ID claimWithoutConflictSquares() {

        Map<ID,Integer> squaresNumberByClaimId = new HashMap<>();

        for (Map.Entry<Position, List<ID>> positionListEntry : elfClaimIDByPosition.entrySet()) {
            List<ID> ids = positionListEntry.getValue();
            if (ids.size() == 1) {
                Integer numberOfSquare = squaresNumberByClaimId.putIfAbsent(ids.get(0), 1);
                if(numberOfSquare != null) {
                    squaresNumberByClaimId.put(ids.get(0),numberOfSquare+1);
                }
            }
        }

        for (Map.Entry<ID, Integer> idIntegerEntry : squaresNumberByClaimId.entrySet()) {
            for (ElfClaim elfClaim : elfClaims) {
                if (elfClaim.id().equals(idIntegerEntry.getKey()) && idIntegerEntry.getValue().equals(elfClaim.squaresNumber())) {
                    return idIntegerEntry.getKey();
                }
            }
        }

        return new ID(-1);
    }

    private static List<String> input(String fileName) throws IOException {

        List<String> lines = new ArrayList<>();

        InputStream is = Frequency.class.getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;

        while ((line = reader.readLine()) != null){
            lines.add(line);
        }

        return  lines;
    }
}
