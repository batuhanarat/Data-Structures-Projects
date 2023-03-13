    package crashtable;
    import java.util.ArrayList;
    public class CrashTable {
        private static final int ROLLING_CONSTANT = 89;
        private static final int MAX_CONFLICTS = 10;
         EntryNode[] table = new EntryNode[8];



        private static class EntryNode {
            // TODO
            String key;
            Object value;
            EntryNode next;


        }


        // TODO: class attributes

        private int hash(String key)  {
            int hash=0;
            for(int i=0; i< key.length(); i++) {
                hash = (key.charAt(i) + (ROLLING_CONSTANT * hash)) % (table.length);
            }
            return hash%(table.length);

        }


        public Object get(String key) {
            // TODO
           int index =hash(key);

           if (table[index]==null) {
               return null;
           } else {
               return searchKey(key,index);
           }

            }

    // bu fonsksiyon verilen hash indexinde bendeki key ile eslesen bir node var mi onu donduruyor, yoksa null
         private Object searchKey(String key,int index) {

            //this returns null in two case.
            //in first case there is no node at given table index -> linked list is empty
            // in second case there is node(s) but no such element with given key  -> linked list is not empty but key is not present
            EntryNode temp = table[index];
            if(temp==null) {
                return null;
            }
            if(temp.key.equals(key)) {
                return temp; }

            while(temp.next!=null) {
                temp = temp.next;
               if( temp.key.equals(key)) {
                   return temp;
               }
            }
            return null; }

         public Object get(String key, Object def) {
            // TODO
            Object temp = get(key);
              if(temp== null) {
                  return def;
              } else  {
                  return temp;
              } }

        public Object put(String key, Object value) {
        // TODO
        int nodeNum=1;
            int index = hash(key);
            EntryNode temp = (EntryNode) searchKey(key,index);
            //place the node
            if(temp == null ) {
                EntryNode currentNode = new EntryNode();
                currentNode.value = value;
                currentNode.key = key;

                //This is the case that in the given index at the array, there is linked list, so your node should be added end of the linked list
                if (table[index] != null) {
                    currentNode = table[index];
                    nodeNum++;

                    while (currentNode.next != null) {
                        nodeNum++;
                        currentNode = currentNode.next;
                    }
                    EntryNode newNode = new EntryNode();
                    newNode.value = value;
                    newNode.key = key;
                    currentNode.next = newNode;
                    if ( nodeNum == MAX_CONFLICTS) {
                        resize(); }
                    return null;
                } else {
                    //This is the case that in the given index at the array, there is no node so you need to add one.
                    table[index] = currentNode;
                    return null;
                } } else {
                //this is the case that key is duplicate, we need to change its value and return the
                EntryNode temp2 = temp;
                temp.value=value;
                return temp2.value;
            } }


        public Object remove(String key) {
            // TODO
            int index = hash(key);
           if (searchKey(key,index) == null ) {
               return null; }
            EntryNode temp = table[index];
            EntryNode removal = temp;

            if(temp.key.equals(key)) {
                if(temp.next!=null) {
                   table[index]= temp.next;
                    temp=null;
                    return removal.value;
                } else {
                    table[index]=null;
                    temp=null;
                    return removal.value;
                } }

                    while( !temp.next.key.equals(key) )    {
                        temp = temp.next; }
                     removal = temp.next;
                    if ( removal.next != null ) {
                        temp.next=removal.next;
                    } else {
                        temp.next = null; }

                    return removal.value;
        }

        public String[] getKeys() {
            // TODO

            ArrayList<String> keys = new ArrayList<String>();

            for( int i=0; i<table.length ; i++) {
                EntryNode temp = table[i];
                int LLsize =sizeOfLinkedList(table[i]);
                for(int j=0 ; j < LLsize; j++) {
                    keys.add(temp.key);

                    if ( j != LLsize-1)
                        temp=temp.next;

                } }

           String[] keysArray = keys.toArray(new String[keys.size()]);

            return keysArray;

        }

        private int sizeOfLinkedList(EntryNode initialNode) {

            if(initialNode == null)
                return 0;

            int size =1;
            while(initialNode.next!=null) {
                size++;
                initialNode= initialNode.next; }
            return size;
        }


        public void resize() {
            // TODO
            EntryNode[] oldTable = table;
            table = new EntryNode[table.length * 2];
            refill(oldTable);

        }

        private void refill(EntryNode[] oldTable ) {

            for(int i =0; i< oldTable.length; i++) {
                EntryNode temp = oldTable[i];

                if (temp ==null)
                    continue;

                for(int j=0; j < sizeOfLinkedList(oldTable[i]); j++) {
                    put(temp.key, temp.value);

                    if (temp.next != null)
                        temp = temp.next;

                } } }

            public void printTable() {

            for(int i=0 ; i<table.length ; i++){
                EntryNode temp = table[i];
                System.out.printf("\nLinked List %d :\n", i );

                if (temp ==null)
                    continue;

                for(int j=0; j < sizeOfLinkedList(table[i]); j++) {
                    System.out.printf("Node %d- Key: %s | Value: %s \n" ,j,temp.key,temp.value.toString());

                    if(temp.next!=null)
                        temp=temp.next;

                } } }

    }
