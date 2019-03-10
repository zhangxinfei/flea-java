package com.soecode.lyf.enums;

/**
 * 使用枚举表述常量的状态
 */
public  class WizardAuditEnum {
	//登录状态
	public enum StatusEnum {

		STATUS_SUCCESS(10, "成功"), STATUS_FAIL(20,"失败");

		private int value;

		private String desc;

		private StatusEnum(int value, String desc) {
			this.value = value;
			this.desc = desc;
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

		public static String getDesc(Integer value) {
			for (StatusEnum status : StatusEnum.values()) {
				if (status.getValue() == (value)) {
					return status.desc;
				}
			}
			return null;
		}
	}

	//存在状态
	public enum existenceStatusEnum {

		STATUS_SUCCESS(10, "存在"), STATUS_FAIL(20,"不存在");

		private int value;

		private String desc;

		private existenceStatusEnum(int value, String desc) {
			this.value = value;
			this.desc = desc;
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

		public static String getDesc(Integer value) {
			for (StatusEnum status : StatusEnum.values()) {
				if (status.getValue() == (value)) {
					return status.desc;
				}
			}
			return null;
		}
	}


	//token验证状态
	public enum tokenStatusEnum {

		//token验证失败
		CHECK_AUTHTOKEN_SUCCESS(10,"验证成功"),//错误编码
		CHECK_AUTHTOKEN_FAIL(20,"验证失败");//错误编码

		private int value;

		private String desc;

		private tokenStatusEnum(int value, String desc) {
			this.value = value;
			this.desc = desc;
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

		public static String getDesc(Integer value) {
			for (StatusEnum status : StatusEnum.values()) {
				if (status.getValue() == (value)) {
					return status.desc;
				}
			}
			return null;
		}
	}

}

