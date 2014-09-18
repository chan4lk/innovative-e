import java.util.LinkedList;


public class TestApp {
	LinkedList<DownloadInfo> infoList;
	
	public TestApp()
	{
		infoList = new LinkedList<DownloadInfo>();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TestApp app = new TestApp();
		app.Save();	

	}
	
	public void Save(){
		for (DownloadInfo info : getData()) {
			System.out.println(String.format("%s %s",info.getDownloadFromUrl(), info.getDownlodToUrl()));
		}
	}
	
	/**
	 * Use this method at the browse button
	 */
	public void Browse(){
		
		infoList.add(new DownloadInfo("http://google.com", "C://data"));
		infoList.add(new DownloadInfo("http://yahoo.com", "d://hansi"));	
	}
	public LinkedList<DownloadInfo> getData()
	{
		
		return infoList;
	}

}

class DownloadInfo{
	private String downloadFromUrl;
	private String downlodToUrl;
	
	public DownloadInfo(String urlFrom, String urlTo)
	{
		this.downloadFromUrl = urlFrom;
		this.downlodToUrl = urlTo;
	}

	/**
	 * @return the downloadFromUrl
	 */
	public String getDownloadFromUrl() {
		return downloadFromUrl;
	}

	/**
	 * @return the downlodToUrl
	 */
	public String getDownlodToUrl() {
		return downlodToUrl;
	}
	
	
}
