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

