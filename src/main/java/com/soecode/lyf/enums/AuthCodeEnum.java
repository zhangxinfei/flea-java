package com.soecode.lyf.enums;

/**
 * Created by wxl on 2018/7/28.
 * 权限编码
 */
public enum AuthCodeEnum {
     AUTH_DEFAULT("00000", "00000", "允许访问"),

     /******************产品管理***************************/
     SY_BUDGET_INVEST_0000001("VIEW", "PAGE_0401020000", "CP", "SEE","INDUSTRIAL,AREA,PARTITION"),//产品管理_查看
     SY_BUDGET_INVEST_0000002("EDIT", "PAGE_0401020000", "CP", "SAVE","INDUSTRIAL,AREA,PARTITION"),//产品管理_保存
     SY_BUDGET_INVEST_0000003("EDIT", "PAGE_0401020000", "CP", "UPD","INDUSTRIAL,AREA,PARTITION");//产品管理_更新     SY_BUDGET_INVEST_0000004("EDIT", "PAGE_0401020000", "CP", "DEL","INDUSTRIAL,AREA,PARTITION"),//产品管理_删除






   /* String auth() default "VIEW"; //操作权限;
    String authsCode();//权限代码
    String typeCode();//操作类型 业务精灵 资源精灵 管理平台 运营平台
    String operating();//操作 上报 ，保存 ，删除*/

     /**权限标识 */
     private String auth;//authId

     /**权限编码 */
     private String authCode;

     /**权限名称 */
     private String authType;//authType

     /**权限描述 */
     private String authDesc;

    /**
     * 参数校验
     */
    private String authParams ;//

    /**
       *
       * 描述：权限编码
       * @author wxl
       * @param auth 操作权限
       * @param authCode 权限编码
       * @param authType 权限类型
       * @return
       */
    private AuthCodeEnum(String auth, String authCode, String authType) {
        this.auth = auth;
        this.authCode = authCode;
        this.authType = authType;
    }

     /**
         *
         * 描述：权限编码
         * @since
         * @param auth 操作权限
         * @param authCode 权限编码
         * @param authType 权限类型
         * @param authDesc 权限描述
         * @return
      */
        private AuthCodeEnum(String auth, String authCode, String authType, String authDesc, String authParams) {
             this.auth = auth;
             this.authCode = authCode;
             this.authType = authType;
             this.authDesc = authDesc;
             this.authParams = authParams;
         }

         public String getAuth() {
         return auth;
    }

         public void setAuth(String auth) {
         this.auth = auth;
     }

         public String getAuthCode() {
         return authCode;
     }

         public void setAuthCode(String authCode) {
         this.authCode = authCode;
     }

         public String getAuthDesc() {
         return authDesc;
     }

         public void setAuthDesc(String authDesc) {
         this.authDesc = authDesc;
     }

         public String getAuthType() {
                 return authType;
         }

         public void setAuthType(String authType) {
                 this.authType = authType;
          }

         public String getAuthParams() {
            return authParams;
        }

         public void setAuthParams(String authParams) {
            this.authParams = authParams;
        }

         @Override
         public String toString(){
             return String.format("auth:%s, authCode:%s, authType:%s, authDesc:%s, authParams:%s", this.auth, this.authCode, this.authType, this.authDesc,this.authParams);
         }

}
