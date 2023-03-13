import java.util.Objects;

class Playlist
{
    Node head = null;
    Node tail = null;

    class Node
    {
        Song data;
        Node next;
        Node prev;
        Node(Song d)
        {
            data = d;
            next = null;
            prev = null;
        }
    }

    public void insertToEnd(Song new_song) {
        // Your code here
        if (new_song == null) {throw new NullPointerException("There is no song"); }

        //In case new element is the first element of the linked list
        if(this.head == null) {
            Node nw1 = new Node(new_song);

            head=nw1;
            nw1.next=nw1;
            tail=nw1;
            nw1.prev=nw1;
        } else {
            //for linked list n>1 elements
            Node nw2= new Node(new_song);

            nw2.next=head;
            nw2.prev=tail;
            tail.next=nw2;
            tail=nw2;
            head.prev=tail;
        }
    }

    public void insertToIndex(Song new_song, int index) {
        // Your code here
        if (new_song == null) {
            throw new NullPointerException("There is no song"); }

        int size = listSize();

        //Insertion for empty lists with appropiate index which is 1.(Only possible index)
        if(size==0 & index==1) {
            Node song = new Node(new_song);
            song.next = song;
            song.prev = song;
            head = song;
            tail = song; }

        //Insertion of the index with value size+1 is same as insertion to end which is allready defined.
        else if(index==size+1) {
            insertToEnd(new_song);
        }
        //Insert function for the insertion of the song with given appropiate index to the non-empty lists.
        else if(index >= 1 & index < size + 1) {
            insert(index,new_song);

        }
    }

    public void removeSong(String song_name){
        // Your code here
        int size = listSize();

        Node temp=head;
        for(int i =1 ; i<=size; i++){

            if (Objects.equals(temp.data.getName(), song_name)) {
                temp.prev.next=temp.next;
                temp.next.prev=temp.prev;

                if(i==1) {
                    head=temp.next;
                } else if (i==size) {
                    tail=temp.prev;}

                temp =null;
                if(size==1) {
                    head=null;
                    tail=null;}

                break;

            }
            temp=temp.next;

        }
    }

    public void move(String song_name, int move_num ) {
        // Your code here
        int size= listSize(); //5
        Node temp=head;
        Node temp3=null; //for index=size new tail should be adjusted by temp3

        for(int index =1 ; index<=size; index++) {

            if (Objects.equals(temp.data.getName(), song_name)) {
                Node temp2=temp;

                if(move_num < index) {

                    for(int a=1; a<=move_num;a++) {
                        temp2=temp2.prev;
                    }
                    if(index==size) {
                        temp3= temp.next;
                    }
                    //to set a connections of previous and next songs of moved song
                    temp.prev.next=temp.next;
                    temp.next.prev=temp.prev;
                    //to connect moved song to its new place
                    temp.next=temp2;
                    temp.prev=temp2.prev;
                    //to break connections of old song which used be at moved song location
                    temp2.prev=temp;
                    temp.prev.next=temp;

                    if(move_num == (index-1)) {
                        //if we are moving as index-1, moved element should be adjusted for new head.
                        head=temp;
                    } else if (index==size) {
                        tail=temp3; }
                    break;
                }
            }
            temp=temp.next;
        }
    }

    public void reverseSequence(int first_ind, int second_ind){
        //Your code here
        int size = listSize();
        //Checking conditions for reverse
        if(size>1) {
            if (first_ind < second_ind) {
                if (first_ind >= 1) {
                    if (second_ind <= size) {
                        reverse(first_ind,second_ind);
                    }
                }
            }
        }
    }

    //Your code here if you use helper methods

    public void displayList() {
        Node temp = head;

        if (temp == null){
            System.out.println("Playlist is empty!");
            return;
        }

        while (temp.next != head)
        {
            temp.data.displaySong();
            temp = temp.next;
        }
        temp.data.displaySong();
    }

    /*This function reverses the list among given indexes.
    I use temp2 for second_ind song and temp for first_ind song
    temp3 is next node after the song with second_ind
    temp2 is my first iterator. iterator will be keep changing as the new previous node of temp3 when loop has begun.
    In the loop I move my iterator to as it becomes previous of temp.To do this I need to move (second_ind-first_ind) times.
    After that, until my temp's next node becomes temp3,I will keep moving my iterator by decreasing value of second_ind-first_ind.

    Short demonstration of reverse(1,4) will be:

    1 2 3 4 5
    4 1 2 3 5
    4 3 1 2 5
    4 3 2 1 5

*/
    public void reverse(int first_ind,int second_ind){
        Node temp = head;
        Node temp2= temp;
        Node temp3=temp;

        for(int i =1; i<first_ind;i++) {
            temp=temp.next; }
        for(int i =1; i<second_ind;i++) {
            temp2=temp2.next;}

        Node iteration=temp2;
        temp3=temp2.next;
        int mov= second_ind-first_ind;

        while(temp.next!=temp3) {

            move(iteration.data.getName(),mov);
            iteration=temp3.prev;
            mov--;

        }
    }

    //Helper method for insertToIndex
    public void insert(int index,Song new_song) {
        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }

        Node song2 = new Node(new_song);
        song2.next = temp;
        song2.prev = temp.prev;
        temp.prev.next = song2;
        temp.prev = song2;

        if (index == 1)
            head = temp.prev;
    }

    //Returning the size of the double linked list
    public int listSize() {

        int counter=1;
        Node temp=head;
        //For empty playlist
        if(head==null)
            return 0;

        while(temp.next!=head) {
            temp=temp.next;
            counter++;
        }

        return counter;
    }
}
