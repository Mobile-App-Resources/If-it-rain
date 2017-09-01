package root.if_it_rains.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root1 on 2017. 9. 1..
 */

@SuppressWarnings("serial")
public class ListForBundle implements Serializable{

    private List list;

    public List getList() {
        return list;
    }

    public ListForBundle(List list){
        this.list = list;
    }
}
