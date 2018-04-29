package wordladder;
import java.util.*;
import java.io.File;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam; 

public class App {
	@RequestMapping(value = "/wordladder") 
	public static void main(@RequestParam(value = "beginWord") String beginWord, @RequestParam(value = "endWord") String endWord) throws Exception{
		File file = new File("./smalldic.txt");
		Scanner dicscanner = new Scanner(file);//scanner of file
		Set<String> dic = new HashSet<String>();//浣跨敤set浣滀负dictionary
		while(dicscanner.hasNextLine()){
			dic.add(dicscanner.nextLine());//璇诲彇鏂囦欢涓崟璇嶆斁鍏ictionary
		}
		dicscanner.close();
		
		App wl = new App();//瀹炰緥wl浠ヨ皟鐢ㄩ潪闈欐�佸嚱鏁�
		String res = wl.ladder(beginWord,endWord,dic);
	}
	
	public String ladder(String beginWord, String endWord, Set<String> dic) throws Exception {  
        if (beginWord.length() == 0 || endWord.length() == 0  || beginWord.length() != endWord.length()){
        	System.out.println("there is something wrong!\n");
        	return null; 
        }
        Map<String,String> path = new HashMap<String,String>();
        Set<String> dictionary = new HashSet<String>(dic);  
        if (dictionary.contains(beginWord))  
            dictionary.remove(beginWord);  
        Queue<String> queue = new LinkedList<String>();  
        queue.add(beginWord);
  
        while (!queue.isEmpty()) {  
            String word = queue.remove();  //鍒犻櫎绗竴涓�
            for (int i = 0; i < word.length(); i++) {  
                char[] wordd = word.toCharArray();  
                for (char j = 'a'; j <= 'z'; j++) {  
                    wordd[i] = j;  
                    String newword = new String(wordd);  
                    
                    if (dictionary.contains(newword)) {  
                    	
                    	path.put(newword,word);
                        if (newword.equals(endWord)) {
                        	
                        	String noww = newword;
                        	String all = noww;
                        	System.out.println(noww);
                        	while(path.get(noww)!= null) {
                        		String prenext = path.get(noww);
                        		System.out.println("->" + prenext );
                        		all += ("->" + prenext );
                        		noww = prenext;
                        	}
                        	return all;
                        }
                        queue.add(newword);  
                        dictionary.remove(newword);  
                        
                    }  
                }  
            }  
            
        }  
        System.out.println("no such path");
        return null;
    }  
}

