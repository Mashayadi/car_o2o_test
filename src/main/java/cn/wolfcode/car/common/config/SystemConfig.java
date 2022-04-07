package cn.wolfcode.car.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "system.car")
public class SystemConfig
{
    /** 项目名称 */
    private static String name;

    /** 版本 */
    private static String version;

    /** 版权年份 */
    private static String copyrightYear;

    /** 实例演示开关 */
    private static boolean demoEnabled;

    /** 上传路径 */
    private static String profile;

    /** 获取地址开关 */
    private static boolean addressEnabled;

    public static String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        SystemConfig.name = name;
    }

    public static String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        SystemConfig.version = version;
    }

    public static String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        SystemConfig.copyrightYear = copyrightYear;
    }

    public static boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        SystemConfig.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        SystemConfig.profile = profile;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        SystemConfig.addressEnabled = addressEnabled;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
}
