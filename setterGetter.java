import java.lang.reflect.*;
import java.io.*;
import java.util.*;
class main{
	public static void main(String gg[]){
		if(gg.length!=2){
			System.out.println("Please look at your input.");
			return;
		}
		String className;
		className = gg[0];
		try{
			ArrayList<String> list;
			list = new ArrayList<String>();
			Class c= Class.forName(className);
			Field[] field = c.getDeclaredFields();
			String line;
			for(int i=0 ; i<field.length ; i++){
				String setterName;
				String getterName;
				line="public void set"+field[i].getName()+"("+ field[i].getType()+" "+field[i].getName()+"){";
				list.add(line);
				line="this."+field[i].getName()+"="+field[i].getName()+";";
				list.add(line);
				list.add("}");
				line="public "+field[i].getGenericType()+" get"+field[i].getName()+"(){";
				list.add(line);
				line="return this."+field[i].getName()+";";
				list.add(line);
				list.add("}");
			}
			if(gg[1].equalsIgnoreCase("constructor=true")){
				line="public "+gg[0]+"(){";
				list.add(line);
				for(int i=0; i<field.length; i++){
					line="this."+field[i].getName()+"="+getDefaultName(field[i].getType());
					list.add(line);
				}
				list.add("}");
 			}
			else if(gg[1].equalsIgnoreCase("constructor=false")){
				
			}else{
				System.out.println("Please Enter if you want constructor.");
				return;
			}
			File f = new File("tmp.tmp");
			if(f.exists()) f.delete();
			RandomAccessFile raf= new RandomAccessFile("tmp.tmp","rw");
			Iterator<String> iter=list.iterator();
			while(iter.hasNext()){
				line=iter.next();
				raf.writeBytes(line+"\r\n");
			}
			raf.close();
			System.out.println("Done successfuly");
		}catch(Exception e){

			
		}
	}
	public static String getDefaultName(Class c){
		String className = c.getName();
		if(className.equals("java.lang.Long") || className.equals("long")) return "0";
		if(className.equals("java.lang.Integer") || className.equals("int")) return "0";
		if(className.equals("java.lang.Character") || className.equals("char")) return "' '";
		if(className.equals("java.lang.String") || className.equals("String")) return "null";
		if(className.equals("java.lang.Boolean") || className.equals("boolean")) return "flase";
		if(className.equals("java.lang.Float") || className.equals("float")) return "0.0f";
		return "null";
	}
}