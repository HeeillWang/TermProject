package com.example.heeill.termproject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

//시험용2

//HashMap을 이용해 사전을 저장하는 해쉬테이블을 구현한다.
//또한 사전을 관리하기 위해서 검색, 삭제, 삽입, 출력, 저장, 로드 등의 메소드를 구현한다.
public class Dictionary {
	static private HashMap<String, String> dic = new HashMap<String, String>(); //사전을 저장할 HashMap

	//word를 사전에서 찾아서 의미를 반환해준다.
	public String Search(String word){
		String mean;
		
		if(dic.isEmpty()){ //사전이 비어있을 경우
			//System.out.println("사전이 비어있습니다.");
			return null;
		}

		mean =dic.get(word);
		
		if(mean == null){ //찾으려는 단어가 사전에 없는 경우
			//System.out.println("해당하는 단어가 사전에 존재하지 않습니다.");
			return null;
		}
		else {
			//System.out.println(word + "의 뜻 : " + mean);
			
			return mean;
		}
			
	}

	//newWord를 key로 newMeaning을 value로 하여 사전에 추가한다.
	//newWord가 이미 사전에 존재하면 false를 반환, 성공적으로 삽입하면 true 반환
	public boolean Insert(String newWord, String newMeaning){
		if(dic.get(newWord)==null){ //추가하려는 단어가 사전에 없을 때
			dic.put(newWord, newMeaning);		
			return true;
		}

		else{ //추가하려는 단어가 사전에 이미 존재할 때
			//System.out.println("사전에 같은 단어가 이미 존재합니다.");
			return false;
		}
	}
	
	//delWord를 사전에서 찾아 삭제한다.
	//delWord가 사전에 존재하지 않으면 false , 성공적으로 삭제하면 true 반환
	public boolean Delete(String delWord){
		if(dic.isEmpty()){ //사전이 비어있을 경우
			//System.out.println("사전이 비어있습니다.");
			return false;
		}
		
		if(dic.remove(delWord)==null) //사전에서 삭제하려는 요소를 찾지 못할 경우
		{
		    //System.out.println("해당하는 단어가 사전에 존재하지 않습니다.");
			return false;
		}
		else{ 
			//System.out.println(delWord + "가 사전에서 삭제되었습니다.");
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
			//System.out.println("사전이 비어있습니다.");
			return "Dictionary is empty";
		}

		if((index > dic.size()) || (index < 1)){ // 찾으려는 index번째가 사전 크기에 맞지 않을 경우
			return "Over Size";
		}

		for(int i=0;i<index;i++) // 입력받은 숫자만큼 다음으로 넘겨서 word에 저장
		{
			word = it.next();
		}
		return word;
	}

	//사전의 모든 요소 출력
	public void Print(){
		System.out.println("현재 사전에 들어 있는 단어 수 : " + dic.size());
		
		Set<String> keys = dic.keySet();
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()){
			String key = it.next();
			//System.out.println(key + " : " + dic.get(key));
		}
	}
	
	/*
	 * dic에 저장된 사전을 dictionary.txt로 저장해준다.
	 * dic에 저장되어 있는 요소가 없어도 파일을 생성하고 빈 파일로 저장한다.
	 * 
	 * dictionary.txt에서 사전의 요소는
	 * word
	 * meaning
	 * 와 같이 계속 개행하며 저장된다.
	 */
	public void Save(){
	
		try{
			File dst = new File("dictionary.txt");
			FileWriter fw = new FileWriter(dst);
			BufferedWriter writer = new BufferedWriter(fw);
			Set<String> keys = dic.keySet();
			Iterator<String> it = keys.iterator();
			

		    while(it.hasNext()){
				String key = it.next();
				writer.write(key);
				writer.newLine();
				writer.write(dic.get(key));
				writer.newLine();
			}
		    
		    writer.close();
		    fw.close();
		}catch(IOException e){
			//System.out.println("File error!");
		}
		return;
	}
	
	//이전에 저장한 사전 파일을 읽어들여서 dic에 저장한다.
	public void Load(){
		try{
			File src = new File("./dictionary.txt");
			FileReader fr = new FileReader(src);
			BufferedReader reader = new BufferedReader(fr);
			String word,mean;
			
			while((word=reader.readLine()) != null){ //파일의 마지막줄까지 읽어온다.
				mean=reader.readLine();
				dic.put(word, mean);
			}
			
			if(dic.size()==0)//이전에 저장한 빈 파일만 존재할 경우
				//System.out.println("이전에 저장한 사전이 비어있습니다. 사전을 시작합니다.");
			
			fr.close();
			reader.close();
			
			return;
		}catch(IOException e){ //이전에 저장한 파일이 없는 경우
			//System.out.println("이전에 저장한 사전이 없습니다. 사전을 시작합니다.");
			return;
		}
	}
}
