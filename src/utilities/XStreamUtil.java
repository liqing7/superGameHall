package utilities;

import com.thoughtworks.xstream.XStream;

/**
 * convert between xml and object
 * 
 * @author LiQing
 *
 */
public class XStreamUtil {
	
	private static XStream xstream = new XStream();
	
	/**
	 * Convert xml to Object
	 * @param xml
	 * @return
	 */
	public static Object fromXML(String xml) {
		return xstream.fromXML(xml);
	}
	
	/**
	 * Convert Object to xml
	 * @param obj
	 * @return
	 */
	public static String toXML(Object obj) {
		String xml = xstream.toXML(obj);
		//Remove empty \n
		String a = xml.replaceAll("\n", "");
		String s = a.replaceAll("\r", "");
		return s;
	}
}