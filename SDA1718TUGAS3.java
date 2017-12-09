// BuffferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

// BufferedWriter
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

// IOException
import java.io.IOException;

// Utility
import java.util.StringTokenizer;

import java.util.Iterator;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SDA1718TUGAS3
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		final Object EOF = null;
		StringTokenizer st;
		String inp;
		
		MagicStorageSystem mss = new MagicStorageSystem();
		while ((inp = in.readLine()) != EOF) {
			st = new StringTokenizer(inp);
			String cmd = st.nextToken();
			if (cmd.equals("add")) {
				String newFolderName = st.nextToken();
				String parentFolderName = st.nextToken();
				out.println("add " + newFolderName + " to " + parentFolderName);
			}
			else if (cmd.equals("insert")) {
				String fileNameExt = st.nextToken();
				int fileSize = Integer.parseInt(st.nextToken());
				String parentFolderName = st.nextToken();
				out.println("insert " + fileNameExt + " with size " + fileSize + " to " + parentFolderName);
			}
			else if (cmd.equals("remove")) {
				String objectName = st.nextToken();
				out.println("delete " + objectName + " from the whole tree");
			}
			else if (cmd.equals("search")) {
				String objectName = st.nextToken();
				out.println("search " + objectName + " in the whole tree");
			}
			else if (cmd.equals("print")) {
				String folderName = st.nextToken();
				out.println("print contents of " + folderName);
			}
			else if (cmd.equals("recommend")) {
				String query = st.nextToken();
				String parentFolderName = st.nextToken();
				out.println("recommend suggestions with prefix " + query + " in " + parentFolderName);
			}
			else if (cmd.equals("move")) {
				String folderName = st.nextToken();
				String targetFolderName = st.nextToken();
				out.println("move " + folderName + " to " + targetFolderName);
			}
			else if (cmd.equals("cut")) {
				String fileName = st.nextToken();
				String originFolderName = st.nextToken();
				String targetFolderName = st.nextToken();
				out.println("move all files named " + fileName + " within " + originFolderName + " to " + targetFolderName);
				
			}
			out.flush();
		}
	}
}

class MagicStorageSystem
{
	private MagicFolder root;
	
	public MagicStorageSystem()
	{
		this.root = new MagicFolder("root");
	}
	
	public String add(String folderName, String parentFolderName)
	{
		return null;
	}
	
	public String insert(String fileNameExt, int fileSize, String parentFolderName)
	{
		return null;
	}
	
	public String remove(String objectName)
	{
		return null;
	}
	
	public String search(String objectName)
	{
		return null;
	}
	
	public String print(String folderName)
	{
		return null;
	}
	
	private abstract class MagicStorageObject
	{
		protected String name;
		protected int size;
		
		public MagicStorageObject(String name)
		{
			this(name,0);
		}
		
		public MagicStorageObject(String name, int size)
		{
			this.name = name;
			this.size = size;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public int getSize()
		{
			return this.size;
		}
	}

	private class MagicFolder extends MagicStorageObject implements Comparable<MagicFolder>
	{
		private MySortedLinkedList<MagicStorageObject> contents;
		
		public MagicFolder(String name)
		{
			super(name);
		}
		
		public int compareTo(MagicFolder other)
		{
			return this.name.compareTo(other.name);
		}
	}

	private class MagicFile extends MagicStorageObject implements Comparable<MagicFile>
	{
		private String type;
		
		public MagicFile(String name, String type, int size)
		{
			super(name);
			this.type = type;
			this.size = size;
		}
		
		public int compareTo(MagicFile other)
		{
			int tmp;
			tmp = this.name.compareTo(other.name);
			if (tmp == 0) {
				tmp = this.type.compareTo(other.type);
				if (tmp == 0) {
					tmp = this.size - other.size;
				}
			}
			return tmp;
		}
	}
}

class MySortedLinkedList<E> extends MyLinkedList<E>
{
	private Comparator<? super E> comparator;
	
	public MySortedLinkedList()
	{
		this(null);
	}
	
	public MySortedLinkedList(Comparator<? super E> comparator)
	{
		this.comparator = comparator;
	}
	
	@SuppressWarnings("unchecked")
	private int compare(E value1, E value2)
	{
		if (this.comparator == null) {
			return ((Comparable<? super E>) value1).compareTo(value2);
		}
		else {
			return this.comparator.compare(value1,value2);
		}
	}
	
	public void add(E value)
	{
		Node<E> tmp = firstNode;
		while (tmp != null && this.compare(value, tmp.value) >= 0) {
			tmp = tmp.next;
		}
		if (tmp == firstNode) {
			super.addFirst(value);
		}
		else if (tmp == null) {
			super.addLast(value);
		}
		else {
			Node<E> newNode = new Node<E>(value);
			Node<E> tmpPrev = tmp.prev;
			tmpPrev.next = newNode;
			newNode.prev = tmpPrev;
			tmp.prev = newNode;
			newNode.next = tmp;
			size++;
		}
	}
	
	public void add(E value, int index)
	{
		throw new UnsupportedOperationException("operation not supported on SortedLinkedList");
	}
	
	public void addFirst(E value)
	{
		throw new UnsupportedOperationException("operation not supported on SortedLinkedList");
	}
	
	public void addLast(E value)
	{
		throw new UnsupportedOperationException("operation not supported on SortedLinkedList");
	}
}

class MyLinkedList<E> implements Iterable<E>
{
	protected int size;
	protected Node<E> firstNode;
	protected Node<E> lastNode;
	
	public MyLinkedList()
	{
		size = 0;
		firstNode = null;
		lastNode = null;
	}
	
	public void add(E value)
	{
		addLast(value);
	}
	
	public void add(E value, int index)
	{
		if (index == 0) {
			addFirst(value);
		}
		else if (index == size) {
			addLast(value);
		}
		else if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			Node<E> newNode = new Node<E>(value);
			Node<E> tmp = firstNode;
			for (int i = 0; i < index; i++) {
				tmp = tmp.next;
			}
			Node<E> tmpPrev = tmp.prev;
			tmpPrev.next = newNode;
			newNode.prev = tmpPrev;
			tmp.prev = newNode;
			newNode.next = tmp;
			size++;
		}
	}
	
	public void addFirst(E value)
	{
		Node<E> tmpFirst = firstNode;
		firstNode = new Node<E>(value);
		firstNode.next = tmpFirst;
		if (tmpFirst != null) {
			tmpFirst.prev = firstNode;
		}
		if (lastNode == null) {
			lastNode = firstNode;
		}
		size++;
	}
	
	public void addLast(E value)
	{
		Node<E> tmpLast = lastNode;
		lastNode = new Node<E>(value);
		lastNode.prev = tmpLast;
		if (tmpLast != null) {
			tmpLast.next = lastNode;
		}
		if (firstNode == null) {
			firstNode = lastNode;
		}
		size++;
	}
	
	public E remove(int index)
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			return removeFirst();
		}
		if (index == size-1) {
			return removeLast();
		}
		Node<E> tmp = firstNode;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next;
		}
		Node<E> tmpPrev = tmp.prev;
		Node<E> tmpNext = tmp.next;
		tmpPrev.next = tmpNext;
		tmpNext.prev = tmpPrev;
		size--;
		return tmp.value;
	}
	
	public E removeFirst()
	{
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<E> tmpFirst = firstNode;
		firstNode = firstNode.next;
		if (firstNode != null) {
			firstNode.prev = null;
		}
		if (lastNode == tmpFirst) {
			lastNode = firstNode;
		}
		size--;
		return tmpFirst.value;
	}
	
	public E removeLast()
	{
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<E> tmpLast = lastNode;
		lastNode = lastNode.prev;
		if (lastNode != null) {
			lastNode.next = null;
		}
		if (firstNode == tmpLast) {
			firstNode = lastNode;
		}
		size--;
		return tmpLast.value;
	}
	
	public void remove(E value)
	{
		Node<E> tmp = firstNode;
		while (!(tmp.value).equals(value)) {
			tmp = tmp.next;
		}
		if (tmp == firstNode) {
			removeFirst();
		}
		else if (tmp == lastNode) {
			removeLast();
		}
		else {
			Node<E> tmpPrev = tmp.prev;
			Node<E> tmpNext = tmp.next;
			tmpPrev.next = tmpNext;
			tmpNext.prev = tmpPrev;
			size--;
		}
	}
	
	//Iterator.remove()
	private void remove(Node<E> current)
	{
		if (current == firstNode) {
			removeFirst();
		}
		else if (current == lastNode) {
			removeLast();
		}
		else {
			Node<E> nextNode = current.next;
			Node<E> prevNode = current.prev;
			nextNode.prev = prevNode;
			prevNode.next = nextNode;
			size--;
		}
	}
	
	public E get(int index)
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			return getFirst();
		}
		if (index == size-1) {
			return getLast();
		}
		Node<E> tmp =  firstNode;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next;
		}
		return tmp.value;
	}
	
	public E getFirst()
	{	
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return firstNode.value;
	}
	
	public E getLast()
	{
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return lastNode.value;
	}
	
	protected static class Node<E>
	{
		E value;
		Node<E> prev;
		Node<E> next;
		
		Node()
		{
			this(null,null,null);
		}
		
		Node(E value)
		{
			this(value,null,null);
		}
		
		Node(E value, Node<E> prev, Node<E> next)
		{
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<E> tmp = firstNode;
		while (tmp != null) {
			sb.append(tmp.value);
			if (tmp.next != null) {
				sb.append(", ");
			}
			tmp = tmp.next;
		}
		sb.append("]");
		return sb.toString();
	}
	
	public void offerFirst(E value)
	{
		addFirst(value);
	}
	
	public void offer(E value)
	{
		add(value);
	}
	
	public void offerLast(E value)
	{
		addLast(value);
	}
	
	public E pollFirst()
	{
		if (isEmpty()) {
			return null;
		}
		return removeFirst();
	}
	
	public E poll()
	{
		if (isEmpty()) {
			return null;
		}
		return pollFirst();
	}
	
	public E pollLast()
	{
		if (isEmpty()) {
			return null;
		}
		return removeLast();
	}
	
	public E peekFirst()
	{
		if (isEmpty()) {
			return null;
		}
		return getFirst();
	}
	
	public E peek()
	{
		if (isEmpty()) {
			return null;
		}
		return getFirst();
	}
	
	public E peekLast()
	{
		if (isEmpty()) {
			return null;
		}
		return getLast();
	}
	
	public boolean isEmpty()
	{
		return (size == 0);
	}
	
	public void clear()
	{
		size = 0;
		firstNode = null;
		lastNode = null;
	}
	
	public Iterator<E> iterator()
	{
		return new MyIterator<E>(this);
	}
	
	private class MyIterator<E> implements Iterator<E>
	{
		private Node<E> current;
		private MyLinkedList<E> list;
		
		public MyIterator(MyLinkedList<E> list)
		{
			this.list = list;
			if (list.isEmpty()) {
				current = null;
			}
			else {
				Node<E> dummy = new Node<E>();
				dummy.next = list.getFirstNode();
				current = dummy;
			}
		}
		
		public boolean hasNext()
		{
			return current != null && current.next != null;
		}
		
		public E next()
		{
			current = current.next;
			return current.value;
		}
		
		public void remove()
		{
			if (list.getFirstNode() == current) {
				list.removeFirst();
			}
			else if (list.getLastNode() == current) {
				list.removeLast();
			}
			else {
				list.remove(current);
			}
		}
	}
	
	public boolean contains(E value)
	{
		Node<E> tmp = firstNode;
		for (int i = 0; i < size; i++) {
			if (value != null && value.equals(tmp.value) || value == null && value == tmp.value) {
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}
	
	private Node<E> getFirstNode()
	{
		return firstNode;
	}
	
	private Node<E> getLastNode()
	{
		return lastNode;
	}
	
	public int size()
	{
		return size;
	}
}

class Pair<S,T>
{
	private S first;
	private T second;
	
	public Pair(S first, T second)
	{
		this.first = first;
		this.second = second;
	}
	
	public S first()
	{
		return this.first;
	}
	
	public T second()
	{
		return this.second;
	}
}