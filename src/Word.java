
public class Word {

	private String content;
	private String category;
	private int length;

	public Word(String content, String category) {
		this.content = content;
		this.category = category;
		this.length = content.length();
	}
	
	public boolean contains(char ch) {
         for (int i = 0; i < length; i++) {
            if (Character.toUpperCase(content.charAt(i)) == ch) {
                return true;
            }
        }

         return false;
       }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
