package cloud_final;

import com.firebase.client.Firebase;

public class ArnoldTest {

	public static void main(String args[]) {
		System.out.println("running...");
		Firebase ref = new Firebase("https://cloudfinal.firebaseio.com/");
		Firebase testRef = ref.child("testing");

		testRef.setValue("Hello");
		System.out.println("done");
	}

}
