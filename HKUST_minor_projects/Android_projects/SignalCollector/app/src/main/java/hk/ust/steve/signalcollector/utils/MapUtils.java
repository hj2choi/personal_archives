package hk.ust.steve.signalcollector.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steve on 27/9/2017.
 */

public class MapUtils {
    private static class AllSiteNames {
        private static final String WongTaiSin = "WongTaiSin";
        private static final String HKUST = "HKUST";
        private static final String UnionHospital = "UnionHospital";
        private static final String Elements = "Elements";
        private static final String PCCW = "PCCW";
    }

    private static final Map<String, SiteInfo> siteInfoMap;

    static {
        siteInfoMap = new HashMap<String, SiteInfo>();
        siteInfoMap.put(AllSiteNames.WongTaiSin, new SiteInfo(AllSiteNames.WongTaiSin, new ArrayList<String>(Arrays.asList("LGF", "GF", "1F")), "LGF"));
        siteInfoMap.put(AllSiteNames.HKUST, new SiteInfo(AllSiteNames.HKUST, new ArrayList<String>(Arrays.asList("1F", "2F")), "2F"));
        siteInfoMap.put(AllSiteNames.UnionHospital, new SiteInfo(AllSiteNames.UnionHospital, new ArrayList<String>(Arrays.asList("GF", "1F", "2F")), "GF"));
        siteInfoMap.put(AllSiteNames.Elements, new SiteInfo(AllSiteNames.Elements, new ArrayList<String>(Arrays.asList("1F", "2F")), "1F"));
        siteInfoMap.put(AllSiteNames.PCCW, new SiteInfo(AllSiteNames.PCCW, new ArrayList<String>(Arrays.asList("1F", "2F")), "2F"));

        curSite = siteInfoMap.get(AllSiteNames.HKUST);
    }

    public static class SiteInfo {
        public SiteInfo(String siteName, ArrayList<String> allAreaIds, String startAreaId) {
            this.siteName = siteName;
            this.allAreaIds = allAreaIds;
            this.startAreaId = startAreaId;
            curAreaId = startAreaId;
        }

        public String getSiteName() {
            return siteName;
        }

        private String siteName;

        public ArrayList<String> getAllAreaIds() {
            return allAreaIds;
        }

        private ArrayList<String> allAreaIds;

        public String getStartAreaId() {
            return startAreaId;
        }

        private String startAreaId;

        public String getCurAreaId() {
            return curAreaId;
        }

        public void setCurAreaId(String id) {
            try {
                if (!allAreaIds.contains(id)) {
                    throw new Exception("the current area id is invalid");
                } else {
                    curAreaId = id;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String curAreaId;
    }

    private static SiteInfo curSite;

    public static SiteInfo getCurSite() {
        return curSite;
    }
}
