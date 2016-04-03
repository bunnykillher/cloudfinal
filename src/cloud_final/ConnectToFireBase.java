package cloud_final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

	public static void run() {
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

	public static void checkWord(String word) {
		GetNewWord getNewWord = new GetNewWord();
		Word tempWord = getNewWord.getWord(word);
		if (tempWord.getDefinition() != null) {
			run2(word);
		}
	}

	public static void run2(final String word) {
		ref = new Firebase("https://cloudfinal.firebaseio.com");
		ref.authWithPassword("arnold.lee.wt@gmail.com", "1234567", new AuthResultHandler() {

			@Override
			public void onAuthenticationError(FirebaseError arg0) {
				System.out.println(arg0);
			}

			@Override
			public void onAuthenticated(AuthData arg0) {
				System.out.println("authenticated");
				Map<String, Object> newWord = new HashMap<String, Object>();
				newWord.put(word, word);
				ref.child("/words").updateChildren(newWord);
			}
		});

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static Word runMyStuff() {

		// TODO: THE CODE THAT YOU NEED HERE.......
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("angelia", Math.random());
		map.put("lx", Math.random());
		map.put("bh", Math.random());
		map.put("sn", Math.random());
		map.put("cc", Math.random());
		map.put("al", Math.random());

		// ref.child("piggy").setValue("hello from eclipse");
		// ref.child("chris").setValue("chris sucks");
		// ref.child("arnold").setValue("sucks too");

		ref.child("from eclipse").updateChildren(map);
		// TODO: THE CODE THAT YOU NEED UNTIL HERE.......

		Query q = ref.child("/words");

		Word words = new Word();
		final GetNewWord getNewWord = new GetNewWord();

		q.addValueEventListener(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.getValue());
				HashMap hash = (HashMap) arg0.getValue();
				Iterator iter = hash.keySet().iterator();
				ArrayList<String> stringValues = new ArrayList<String>();
				while (iter.hasNext()) {
					String string = (String) hash.get(iter.next());
					stringValues.add(string);
				}

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