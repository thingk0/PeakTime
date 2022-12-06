package org.peaktime.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CafeteriaServiceTest {

    @Test
    void getFireStoreData() throws Exception {

        Firestore firestore = FirestoreClient.getFirestore();

        int in = 0;
        int out = 0;

        // given
        DocumentReference inFromFireStore = firestore.collection("RaspberryPi").document("in");
        Collection<Object> objects = Objects.requireNonNull(inFromFireStore.get().get().getData()).values();
        for (Object ob : objects) {
            in = Integer.parseInt((String) ob);
            break;
        }

        System.out.println("in = " + in);

    }

}