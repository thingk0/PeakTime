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

        int in = 0;
        int out = 0;

        try {
            int[] inOut = getInOut();

            in = inOut[0];
            out = inOut[1];

        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }

        // 혼잡
        if (in > out * 1.5) {
            System.out.println("in = " + in);
            System.out.println("out = " + out);
            System.out.println("CONFUSION");
            return "CONFUSION";
        }

        // 여유
        else if (in < out) {
            System.out.println("in = " + in);
            System.out.println("out = " + out);
            System.out.println("RELAX");
            return "RELAX";
        }

        // 준비중
        else if (in == 0 && out == 0) {
            return "PREPARING";
        }

        // 보통
        else {
            System.out.println("in = " + in);
            System.out.println("out = " + out);
            System.out.println("NORMAL");
            return "NORMAL";
        }

    }

    // in 과 out 필드 가져와서 리스트로 반환.
    private int[] getInOut() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();

        // in 필드, out 필드 2가지 뿐이어서 크기는 2로 고정.
        int[] inout = new int[2];

        // in 필드 값 가져와서 inout 배열에 추가.
        DocumentReference inFromFireStore = firestore.collection("RaspberryPi").document("in");
        Collection<Object> in = Objects.requireNonNull(inFromFireStore.get().get().getData()).values();
        for (Object value : in) {
            inout[0] = Integer.parseInt((String) value);
            break;
        }

        // out 필드 값 가져와서 inout 배열에 추가.
        DocumentReference outFromFireStore = firestore.collection("RaspberryPi").document("out");
        Collection<Object> out = Objects.requireNonNull(outFromFireStore.get().get().getData()).values();
        for (Object value : out) {
            inout[1] = Integer.parseInt((String) value);
            break;
        }

        // in, out 값 담은 리스트 반환.
        return inout;
    }
}
