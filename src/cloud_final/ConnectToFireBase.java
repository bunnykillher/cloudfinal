package cloud_final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.processing.Completion;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Firebase.AuthResultHandler;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class ConnectToFireBase {

	static Firebase ref;
	static String s1;
	static Word word;

	public Word getWord() {
		return word;
	}

	// public static void run() {
	// System.out.println("hello");
	// System.out.println("arnold");
	//
	// ref = new Firebase("https://cloudfinal.firebaseio.com");
	// ref.authWithPassword("arnold.lee.wt@gmail.com", "1234567", new AuthResultHandler() {
	//
	// @Override
	// public void onAuthenticationError(FirebaseError arg0) {
	// System.out.println(arg0);
	// }
	//
	// @Override
	// public void onAuthenticated(AuthData arg0) {
	// System.out.println("authenticated");
	// runMyStuff();
	//
	// }
	// });
	//
	// try {
	// Thread.sleep(5000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// System.out.println("done");
	// }

	public static void main(String[] args) {

		System.out.println("hello");
		System.out.println("arnold");

		ref = new Firebase("https://cloudfinal.firebaseio.com");
		ref.authWithPassword("arnold.lee.wt@gmail.com", "1234567", new AuthResultHandler() {

			@Override
			public void onAuthenticationError(FirebaseError arg0) {
				System.out.println(arg0);
			}

			@Override
			public void onAuthenticated(AuthData arg0) {
				System.out.println("authenticated");
				runMyStuff();
			}
		});

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("done");

	}

	public static Word returnResult(Word word) {
		return word;
	}

	public static Word runMyStuff() {
		
		// TODO: THE CODE THAT YOU NEED HERE.......
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("angelia", "24");
		map.put("lx", "25");

		ref.child("piggy").setValue("hello from eclipse");
		// ref.child("chris").setValue("chris sucks");
		// ref.child("arnold").setValue("sucks too");

		ref.child("ages").updateChildren(map);
		// TODO: THE CODE THAT YOU NEED UNTIL HERE.......

		Query q = ref.child("/words");

		Word words = new Word();
		final GetNewWord getNewWord = new GetNewWord();

		q.addValueEventListener(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.getValue());
				ArrayList<String> stringValues = (ArrayList<String>) arg0.getValue();
				Random rand = new Random();
				s1 = stringValues.get(rand.nextInt(stringValues.size() - 1) + 1);
				word = getNewWord.getWordDef(s1);
			}

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
			}

		});
		return words;

	}

}