package Message;

import java.io.Serializable;

public class Message_Plalist implements Serializable {
    /**
     *
     */
    public String[] s;
    public String name;
    public int k;
    public int p;
    public Message_Plalist( String n,String[] a,int i,int l){
        p=l;
        k =i ;
        name = n;
        s = new String[i];
        for(int j=0;j<i;j++){
            s[j]=a[j];
        }
    }
}
