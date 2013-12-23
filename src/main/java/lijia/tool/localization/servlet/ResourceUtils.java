package lijia.tool.localization.servlet;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import lijia.tool.localization.Constant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class ResourceUtils {

    private static final Map RESOURCES = new HashMap();

    private static Log log = LogFactory.getLog(ResourceUtils.class);

    public static ResourceBundle getResourceInstance(Locale locale,String resPFile) {
        ResourceBundle res = null;
        Object obj = RESOURCES.get(locale.toString()+resPFile);
        if (obj == null) {
//            res = ResourceBundle.getBundle(resPFile, new Locale("zh", "CN"));
            res = ResourceBundle.getBundle(resPFile,locale);
            RESOURCES.put(locale.toString()+resPFile, res);
        } else {
            res = (ResourceBundle) obj;
        }
        return res;
    }


    public static String getResource(String pfile, String key) {
        return getResourceInstance(Constant.DEFUALT_LOCALE,pfile).getString(key);
    }
    public static String getResource(Locale locale,String pfile, String key) {
        return getResourceInstance(locale,pfile).getString(key);
    }
    
}
