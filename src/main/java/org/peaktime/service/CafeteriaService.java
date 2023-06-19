package org.peaktime.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class CafeteriaService {

    /**
     * 현재 식당 인원 현황 가져오기.
     */
    public String getCafeteriaStatus() {
        int[] inOut;
        try {
            inOut = getInOut();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }

        return evaluateCafeteriaStatus(inOut[0], inOut[1]);
    }

    private int[] getInOut() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        return new int[] {
                retrieveDataFromFirestore(firestore, "in"),
                retrieveDataFromFirestore(firestore, "out")};
    }

    private int retrieveDataFromFirestore(Firestore firestore, String document) throws Exception {
        DocumentReference docFromFireStore = firestore.collection("RaspberryPi").document(document);
        Collection<Object> data = Objects.requireNonNull(docFromFireStore.get().get().getData()).values();
        for (Object value : data) {
            String result = value.equals("") ? "0" : (String) value;
            return Integer.parseInt(result);
        }

        return 0;
    }

    private String evaluateCafeteriaStatus(int in, int out) {
        if (in == 0 && out == 0) return "PREPARING";
        else if (in > out * 1.5) return "CONFUSION";
        else if (in < out) return "RELAX";
        else return "NORMAL";
    }
}
