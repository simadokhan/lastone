package com.example.test;

import com.google.firebase.firestore.FirebaseFirestore;


public class FireBaseServices {
   private static  FireBaseServices   Instance;
   private   FirebaseFirestore fire;

    public FirebaseFirestore getFire() {
        return fire;
    }

    public FireBaseServices(){
        this.fire=FirebaseFirestore.getInstance();
    }
    public static FireBaseServices getinstance() {
        if (Instance == null) {
            Instance = new FireBaseServices();
        }
        return Instance;
   }
}
