package com.soecode.lyf.enums;

import java.util.UUID;

/**
 * Created by wxl on 2018/6/12.
 */
public class CommonInfoEnum {

    public static int  POI_RAM_DATA_AMOUNT = 1000;//每次缓存1000条到内存
    public static String  POI_SHEET_NAME = "sheet";//sheet主体名称后面追加号码
    public static String  POI_PASSWORD_CODE = "qysldett211985";//sheet主体名称后面追加号码
    public static String  POI_SAYUN_ENCODE =  UUID.randomUUID().toString().replaceAll("-", "");//三预编制下载编码;
    public static String  POI_SAYUNPERSON_ENCODE = UUID.randomUUID().toString().replaceAll("-", "");//三预编制下载编码
    public static String  KEY_REDIES_VALUE = "ECEJ_SANYU_";
    /**
     * 数据访问状态
     */
    public enum accessStatus {

        REST_ACCESS_SUCCESS_STATUS(10, "访问成功"),REST_ACCESS_FAILURE_STATUS(20,"访问失败"),
        REST_ACCESS_ERROR_STATUS(30,"后台异常处理"),REST_TOKEN_TIME_OUT_STATUS(41,"token超时"),
        REST_LOGIN_LADP_STATUS(42,"LADP校验失败"),REST_LOGIN_SYSTEM_STATUS(43,"用户未录入本系统"),
        REST_TOKEN_NOT_EXIST_STATUS(44,"token未存在"),
        REST_TOKEN_ERROR_STATUS(46,"token错误"),
        REST_ACCESS_IP_ERROR_STATUS(47,"登录ip错误"),
        REST_ACCESS_TABLE_ERROR_STATUS(48,"表单影响其他表单数据"),
        REST_ACCESS_KPI_DELETE_INFLUENCES_STATUS(49,"指标已影响其他公式"),
        REST_ACCESS_AUTH_BAD_STATUS(50,"权限错误"),
        REST_ACCESS_REPORT_PARAMS_BAD_STATUS(60,"上报参数错误"),
        REST_ACCESS_REPORT_ALREADY_STATUS(61,"当前页面已上报"),
        REST_ACCESS_DATA_REPEAT_STATUS(70,"数据重复");
        public Integer value;
        public String desc;

        private accessStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (accessStatus c : accessStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * ladp 访问状态
     */
    public enum ladpStatus {

        LADP_SUCCESS_STATUS(1, "访问成功"),LADP_USERNAME_STATUS(-1,"认证失败, 用户不存在"),
        LADP_APASSWORD_STATUS(-2,"认证失败, 密码错误"),LADP_UNKOWN_STATUS(0, "认证失败, 未知原因");
        public int value;
        public String desc;

        private ladpStatus(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(int value) {
            for (accessStatus c : accessStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }



    /**
     * 系统状态变量
     */
    public enum systemStatus {

        SYSTEM_SUCCESS_STATUS(10, "正常"),SYSTEM_DELETE_STATUS(20,"已删除");
        public Integer value;
        public String desc;

        private systemStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (accessStatus c : accessStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum indexShow  {

        INDEX_SUCCESS_SHOW(10, "显示"),INDEX_DELETE_SHOW(20,"不显示");
        public Integer value;
        public String desc;

        private indexShow(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (accessStatus c : accessStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum memberType {

        MEMBER_HOLD_TYPE(10, "控股"),MEMBER_COMPANY_TYPE(20,"企业");
        public Integer value;
        public String desc;

        private memberType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (accessStatus c : accessStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 组织配置权限
     */
    public enum seeAuthCode {

        AUTH_SEE_CODE(10, "查看"),MEMBER_EDIT_CODE(20,"编辑");
        public Integer value;
        public String desc;

        private seeAuthCode(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (accessStatus c : accessStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 系统参数名称代码
     */
    public enum systemParamNameCode {

        SYSTEM_ALL_NAME_CODE("ALL", "全部"),SYSTEM_EMPTY_NAME_CODE("EMPTY", "空值");
        public String value;
        public String desc;

        private systemParamNameCode(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(String value) {
            for (accessStatus c : accessStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 上报状态编码
     */
    public enum reportStatusCode {

        REPORT_SEE_CODE(10, "未编辑"),REPORT_EDIT_CODE(30,"编辑"),REPORT_TURN_DOWN_CODE(40,"驳回"),REPORT_SUCCESS_CODE(20,"上报成功");
        public Integer value;
        public String desc;

        private reportStatusCode(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (reportStatusCode c : reportStatusCode.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 城市站点区分编码
     */
    public enum citySiteCode {

        CITY_CODE(10, "城市"),SITE_CODE(20,"站点");
        public Integer value;
        public String desc;

        private citySiteCode(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (accessStatus c : accessStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    /**
     * redies 存储key
     */
    public enum redisTokenKey {
        //权限token数据key
        REDIES_KEY_MEMBER_AUTH_TOKEN(10, "AUTH_TOKEN_"),
        REDIES_KEY_BD_BUDGET(10, "BD_BUDGET"),
        SITE_CODE(20,"站点");
        public Integer value;
        public String desc;

        private redisTokenKey(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (accessStatus c : accessStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * uuid编码
     */
    public enum UUIDType {
        //预算投资项目
        UUID_BD_BUDGET_INVEST_CODE("UUID_BD_BUDGET_INVEST_CODE", "INVEST_"),
        //预算编制
        UUID_BD_BUDGET_CODE("UUID_BD_BUDGET_CODE","BUDGET_"),
        //配置页面分组id
        UUID_CONFIG_PAGE_GROUP_ID_CODE("UUID_CONFIG_PAGE_GROUP_ID_CODE","PAGE_GROUP_"),
        //配置页面分组code
        UUID_CONFIG_PAGE_GROUP_CODE("UUID_CONFIG_PAGE_GROUP_CODE","PAGE_GROUPCODE_"),
        //上报编码
        UUID_BD_REPORT_CODE("UUID_BD_REPORT_CODE","REPORT_"),
        //表单编码
        UUID_BD_TABLE_CODE("UUID_BD_TABLE_CODE","TABLE_")
        ;


        public String value;
        public String desc;

        private UUIDType(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(String value) {
            for (UUIDType c : UUIDType.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    /**
     * 投资预算版本编码
     */
    public enum budgetVersionCode {

        INVEST_BUDGET_VERSION_INITIAL(10, "年初"),INVEST_BUDGET_VERSION_MIDDLE(20,"年中"),INVEST_BUDGET_VERSION_MONTH(30,"月度");
        public Integer value;
        public String desc;

        private budgetVersionCode(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (budgetVersionCode c : budgetVersionCode.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 公式是否开启整行使用
     */
    public enum formulaOpenRowStatusCode {

        FORMULA_OPEN_CODE(10, "开启"),FORMULA_CLOSE_CODE(20,"未开启");
        public Integer value;
        public String desc;

        private formulaOpenRowStatusCode(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(Integer value) {
            for (formulaOpenRowStatusCode c : formulaOpenRowStatusCode.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    /**
     * 功能费用编码
     */
    public enum functionalRangeCode {

        SALES_COST_SUM_CODE("0100", "销售费用合计"),MANAGEMENT_COST_SUM_CODE("0200","管理费用合计"),SERVICE_COST_SUM_CODE("0300,0400","服务成本"),
        SALES_COST_SUM_TABLE_CODE("XIAOSHOUFEIYONG", "销售费用"),
        SERVICE_COST_SUM_TABLE_CODE("FUWUCHENGBEN","管理费用"),
        MANAGEMENT_COST_SUM_TABLE_CODE("GUANLIFEIYONG","服务成本");
        public String value;
        public String desc;

        private functionalRangeCode(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(String value) {
            for (functionalRangeCode c : functionalRangeCode.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    /**
     * 指标单位
     */
    public enum getUnitCode {

        KPI_UNIT_PERCENT_CODE("UNIT_0000003", "%");
        public String value;
        public String desc;

        private getUnitCode(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public static String getDesc(String value) {
            for (getUnitCode c : getUnitCode.values()) {
                if (c.getValue().equals(value)) {
                    return c.desc;
                }
            }
            return null;
        }


        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


}
