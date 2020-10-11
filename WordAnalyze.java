import java.io.File;
import java.io.FileReader;


public class WordAnalyze {
    public static String removeZero(String str) {
        int len = str.length(), i = 0;
        while (i < len-1 && str.charAt(i) == '0') {
            i++;
        }
        return str.substring(i);
    }
    private String keyWord[] = {"BEGIN","END","FOR","IF","THEN","ELSE"};
    private String keyWordOut[]={"Begin","End","For","If","Then","Else"};
    private char ch;
    //判断是否是关键字
    boolean isKey(String str)
    {
        for(int i = 0;i < keyWord.length;i++)
        {
            if(keyWord[i].equals(str)) {
                System.out.println(keyWordOut[i]);
                return true;
            }
        }
        return false;
    }
    //判断是否是字母
    boolean isLetter(char letter)
    {
        if((letter >= 'a' && letter <= 'z')||(letter >= 'A' && letter <= 'Z'))
            return true;
        else
            return false;
    }
    //判断是否是数字
    boolean isDigit(char digit)
    {
        if(digit >= '0' && digit <= '9')
            return true;
        else
            return false;
    }
    //词法分析
    void analyze(char[] chars)
    {
        String arr = "";
        for(int i = 0;i< chars.length;i++) {
            ch = chars[i];
            arr = "";
            if(ch == ' '||ch == '\t'||ch == '\n'||ch == '\r'){
                continue;
            }
            else if(isLetter(ch)){
                while(isLetter(ch)||isDigit(ch)){
                    arr += ch;
                    ch = chars[++i];
                }
                //回退一个字符
                i--;
                if(isKey(arr)){
                    //关键字
                    //System.out.println(arr+"\t4"+"\t关键字");
                }
                else{
                    //标识符
                    System.out.println("Ident"+"("+arr+")");
                }
            }
            else if(isDigit(ch))
            {
                while(isDigit(ch))
                {
                    //if(ch == '.') i--;
                    arr = arr + ch;
                    ch = chars[++i];
                }
                //属于无符号常数
                i--;
                //前导0的去除
                
                System.out.println("Int"+"("+removeZero(arr)+")");
            }
            else switch(ch){
                    case ':':{
                        if(chars[i+1]=='='){
                            System.out.println("Assign");
                        }
                        else {
                            System.out.println("Colon");
                        }
                        break;
                    }
                    case '+':{
                        System.out.println("Plus");
                        break;
                    }
                    case '*':{
                        System.out.println("Star");
                        break;
                    }
                    case ',':{
                        System.out.println("Comma");
                        break;
                    }
                    case '(':{
                        System.out.println("LParenthesis");
                        break;
                    }
                    case ')':{
                        System.out.println("RParenthesis");
                        break;
                    }
                    default:{
                        System.out.println("Unknown");
                        break;
                    }
                }
        }
    }
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        FileReader reader = new FileReader(file);
        int length = (int) file.length();
        char buf[] = new char[length+10];
        reader.read(buf);
        reader.close();
        //4.str = .replaceAll("\\s*", "");
        new WordAnalyze().analyze(buf.toString().replaceAll("\\s*", "").toCharArray());
    }
}
