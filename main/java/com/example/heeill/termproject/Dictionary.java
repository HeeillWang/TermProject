package com.example.heeill.termproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


//HashMap을 이용해 사전을 저장하는 해쉬테이블을 구현한다.
//또한 사전을 관리하기 위해서 검색, 삭제, 삽입, 출력, 저장, 로드 등의 메소드를 구현한다.
public class Dictionary extends AppCompatActivity {
	static private HashMap<String, Word_info> dic = new HashMap<String, Word_info>(); //사전을 저장할 HashMap

	//word를 사전에서 찾아서 단어 정보를 반환해준다.
	public Word_info Search_info(String word){
		Word_info find_word;
		
		if(dic.isEmpty()){ //사전이 비어있을 경우
			return null;
		}

		find_word =dic.get(word);
		
		if(find_word == null){ //찾으려는 단어가 사전에 없는 경우
			return null;
		}
		else {
			return find_word;
		}
	}

	public String Search(String word){
		Word_info info;

		if(dic.isEmpty()){ //사전이 비어있을 경우
			return null;
		}

		info =dic.get(word);

		if(info == null){ //찾으려는 단어가 사전에 없는 경우
			return null;
		}
		else {
			return info.getMean();
		}
	}

	//newWord를 key로 newMeaning을 value로 하여 사전에 추가한다.
	//newWord가 이미 사전에 존재하면 false를 반환, 성공적으로 삽입하면 true 반환
	public boolean Insert(String newWord, String newMeaning){
		Word_info newword = new Word_info(newMeaning);

		if(dic.get(newWord)==null){ //추가하려는 단어가 사전에 없을 때
			Log.i("TermProject","insert true");
			dic.put(newWord, newword);
			return true;
		}
		else{ //추가하려는 단어가 사전에 이미 존재할 때
			Log.i("TermProject","insert false");
			return false;
		}
	}

	//newWord를 key로 newMeaning을 value로 하여 사전에 추가한다.
	//newWord가 이미 사전에 존재하면 false를 반환, 성공적으로 삽입하면 true 반환
	//load시에만 사용되는 insert
	public boolean Insert(String newWord, String newMeaning, int correct_count){
		Word_info newword = new Word_info(correct_count,newMeaning);

		if(dic.get(newWord)==null){ //추가하려는 단어가 사전에 없을 때
			dic.put(newWord, newword);
			return true;
		}

		else{ //추가하려는 단어가 사전에 이미 존재할 때
			return false;
		}
	}

	public int[] random_index()
	{
		Random random = new Random();
		int maximum = dic.size();
		int[] arr = new int[maximum];
		int idx;
		Log.i("TermProject"," " + maximum);

		for(int i = 0 ;i<maximum;i++)
		{
			arr[i] = random.nextInt(maximum);
			for(int j = 0;j<i;j++)
			{
				if(arr[j]==arr[i])
				{
					i--;
					break;
				}
			}
		}

		return arr;
	}
	
	//delWord를 사전에서 찾아 삭제한다.
	//delWord가 사전에 존재하지 않으면 false , 성공적으로 삭제하면 true 반환
	public boolean Delete(String delWord){
		if(dic.isEmpty()){ //사전이 비어있을 경우
			return false;
		}
		
		if(dic.remove(delWord)==null) //사전에서 삭제하려는 요소를 찾지 못할 경우
		{
			return false;
		}
		else{
			return true;
		}
	}

	// 사전의 크기를 int형으로 반환한다.
	public int ReturnSize()
	{
		return dic.size();
	}

	// 단어를 전부 출력하는데 사용하는 함수
	// 사전에 index번째로 저장되어 있는 단어를 String으로 반환
	// 사전이 비어있으면 비어있다는 메시지 반환하고 사이즈를 넘는 수를 입력하면
	// 사이즈가 넘었다는 메시지 반환

	public String ReturnWord(int index){
		String word = null;
		Set<String> words = dic.keySet();
		Iterator<String> it = words.iterator();

		if(dic.isEmpty()){ //사전이 비어있을 경우
			return "Dictionary is empty";
		}

		if((index >= dic.size()) || (index < 0)){ // 찾으려는 index번째가 사전 크기에 맞지 않을 경우
			Log.i("TemrProject","Oversize");
			return "Over Size";
		}

		for(int i=0;i<=index;i++) // 입력받은 숫자만큼 다음으로 넘겨서 word에 저장
		{
			word = it.next();
		}

		return word;
	}
	
	/*
	 * dic에 저장된 사전을 dictionary.txt로 저장해준다.
	 * dic에 저장되어 있는 요소가 없어도 파일을 생성하고 빈 파일로 저장한다.
	 * 
	 * dictionary.txt에서 사전의 요소는
	 * word
	 * meaning
	 * correct_count
	 * 와 같이 계속 개행하며 저장된다.
	 */
	public void Save(BufferedWriter writer){
		Log.i("TermProject","File Save");
		try{
			/*
			Log.i("TermProject","File Save0");
			fos = openFileOutput(filename,Context.MODE_PRIVATE);
			Log.i("TermProject","File Save1");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
			Log.i("TermProject","File Save2");
			*/

			Set<String> keys = dic.keySet();
			Iterator<String> it = keys.iterator();

		    while(it.hasNext()){
				String key = it.next();
				int correct_count;

				writer.write(key);
				Log.i("TermProject", "File Save1 : "+ key);
				writer.newLine();
				writer.write(dic.get(key).getMean());
				Log.i("TermProject", "File Save2 : "+dic.get(key).getMean());
				writer.newLine();

				correct_count = dic.get(key).getCorrect_count();
				writer.write(correct_count);
				Log.i("TermProject", "File Save3 : "+ correct_count);
				writer.newLine();
			}

			Log.i("TermProject","File Save Success");

		}catch(Exception e){
			Log.i("TermProject","File Save Error");
		}
		return;
	}
	
	//이전에 저장한 사전 파일을 읽어들여서 dic에 저장한다.
	public void Load(BufferedReader reader){
		Log.i("TermProject","File Load");
		try{
			String word,mean;
			int correct_count;


			Log.i("TermProject","File Load1");
			while((word=reader.readLine()) != null){ //파일의 마지막줄까지 읽어온다.
				Log.i("TermProject","File Load2 : " + word);
				mean=reader.readLine();
				Log.i("TermProject","File Load3 : " + mean);
				correct_count = reader.read();
				reader.readLine();
				Log.i("TermProject","File Load4 : " + correct_count);


				this.Insert(word,mean,correct_count);
			}
			Log.i("TermProject","File Load Success");
			return;
		}catch(Exception e){ //이전에 저장한 파일이 없는 경우
			Log.i("TermProject","File Load Error");
			return;
		}
	}


}
