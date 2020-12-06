# l00162025.assign2
WIP generic Arraylist and Linkedlist

Jack R. McIlraith - L00162025
https://github.com/JackMcIlraith/l00162025.assign2


List of known bugs and/or omissions:
Infix Calculator will only work if there are brackets, eg:
2+7 will not work
but (2+7) will

It can handle doubles, and most math opperations (+,-,*,/,%,^)



Rotate: ArrayList vs LinkedList:
The underlying data structure of an ArrayList is a simple array.
While the specific get() and set() functions are very fast and efficient, the remove() method is
excruciatingly slow when compared to other data structures. This is due to having to move each
element of the array after the targeted element back in line.
The underlying structure of a LinkedList is node based, with a pointer being used to link each
element to the next. This means that functions such as get() and set() are slow, as one will have to
iterate through the list to the desired element, however to remove an element is simple, as the
pointer of the previous node is simply set to the node after the node targeted for removal.
For the sake of comparison, both ArrayList and LinkedList employ a similar algorithm to rotate, and
we will only discuss a single rotation to the left.
The ArrayList we add the first element to the end of the list, then remove the first element. This will
require us to then move each element of the ArrayList one to the left, so we can simplify this as O(n)
per rotation.
This is very inefficient as opposed to the LinkedList, where all one has to do is set the tailNode.next
pointer to the current head node, then update the tailNode to the new tail node, then we set the
head node to the headNode.next. This will make the LinkedList a loop, so to break it we simply reset
the tailNode.next to null, breaking the loop. All of these do not require us to iterate through the list
like the ArrayList, so we can consider it to be an O(1) per rotation required.
Neither of these are optimized, as there exists many faster ways to do this, such as the split and
triple reverse method for ArrayList, or moving along the LinkedList while it is looped to the desired
location then breaking the loop. But for the sake of comparing the underlying structures a like for
like algorithm is more interesting to talk about.
