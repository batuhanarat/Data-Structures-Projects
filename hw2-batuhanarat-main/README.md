Homework Description


In this assignment, you will be implementing a Hash Table in Java from scratch. You might have noticed the standard HashTable implementation in Java is a generic class that works with many types. You do not, however, need to make your code generic; for the purposes of this assignment, you will use String as your key and Object as the value



As a brief reminder, hash tables use a hashing function to compute numeric indices from keys and use them to index into their internal buckets, allowing fast lookup. However, since collisions can occur in the hash algorithm, the buckets do not point directly to the values; they instead point to linked list data structures that hold key-value pairs of the same key.

Define a class CrashTable and a private inner class EntryNode. The latter will be your singly linked list:
Define the following attributes in EntryNode:
◦ String key
◦ Object value
◦ EntryNode next
Define as an instance variable. This will be an array of your buckets. Define a constant (explained below)


Computing the hash


The hashing algorithm you will be implementing in this assignment is Polynomial rolling hashing with the prime number 89. You need to modulo the resulting hash with the size of the table array in order to avoid going outside of the array boundary. Notice how the hash values are dependent on the size of the table.


Your Hash Table implementation will be slightly different than how it would normally be implemented. We want our table to use very little memory, so our table array will initially be set to 8 elements. This will cause many conflicts as you can imagine, but we allow a certain number of conflicts (MAX_CONFLICTS) for each bucket. When any bucket reaches the limit, you need to double the size of the table, and rehash every key with the new size.
Implement the following methods in CrashTable:

private static int hash(String key) [10%]
This method will be used by the instances to compute numerical hashes of given keys.


public Object get(String key) [20%]
Do the following steps to implement the method. Note that some steps are repeated for other methods, and will not be repeated:
1. Compute the hash of the given key
2. Look up the index in the bucket. Return null if no such index exists, or the bucket is
empty.
3. If the bucket contains an EntryNode at the given index, traverse through the linked list
to find a node whose key is equal to the given key.
4. If found, return it, otherwise, return null


public Object get(String key, Object def) [5%]
Same as the get method above, but returns the supplied default value if the key is not found.


public Object put(String key, Object value) [20%]
This method inserts a new key-value pair to the table. Similar to the get method, compute the hash of the key and insert the appropriate objects. Note that you may need to resize the table array (and rehash!) if the bucket will exceed MAX_CONFLICTS after the insertion.
If a value already exists at the given key, return it before overwriting it, otherwise return null


public Object remove(String key) [10%]
This method will remove the key-value pair from the table associated with the given key. You are not required to resize the table after this operation.


public String[] getKeys() [10%]
This method will return a String array of every key in the table.


public void resize(int size) [15%]
Resizes the table to a given size. Do not forget to rehash!
