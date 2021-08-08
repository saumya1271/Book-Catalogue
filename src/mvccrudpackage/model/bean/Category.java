package mvccrudpackage.model.bean;

public class Category {

	 protected int Cid;
	 protected String CategoryTitle;
	 
	 public Category() {}
	 public Category(String CategoryTitle) {
		 this.CategoryTitle = CategoryTitle;
	 }
	 
	 public Category(int Cid, String CategoryTitle) {
		 this.Cid = Cid;
		 this.CategoryTitle = CategoryTitle;
	 }
	 
	 public int getCatCid() {
		 return Cid;
	 }
	 
	 public void setCatCid(int Cid) {
		 this.Cid = Cid;
	 }
	 
	 public String getCategoryTitle() {
		 return CategoryTitle;
	 }
	 
	 public void setCategoryTitle(String CategoryTitle) {
		 this.CategoryTitle = CategoryTitle;
	 }
}
